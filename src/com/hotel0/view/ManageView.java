package com.hotel0.view;

import com.hotel0.bean.Book;
import com.hotel0.bean.Hotel;
import com.hotel0.bean.Room;
import com.hotel0.dao.AdminDao;
import com.hotel0.dao.BookDao;
import com.hotel0.dao.HotelDao;
import com.hotel0.dao.RoomDao;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageView {

	JFrame frame;
	private JTextField searchField;
	private JTable hotelTable;
	private JTable roomTable;


	public ManageView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("酒店信息管理系统");
		frame.setBounds(100, 100, 800, 600); // 调整窗口大小
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		// 菜单栏
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		// 管理员操作菜单（仅对用户ID为0000显示）
		if ("0000".equals(LoginView.currentUserId)) {
			JMenu adminMenu = new JMenu("管理员操作");
			menuBar.add(adminMenu);

			JMenuItem addHotelItem = new JMenuItem("录入酒店信息");
			addHotelItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					AddHotelView addHotelView = new AddHotelView();
					addHotelView.setVisible(true);
				}
			});
			adminMenu.add(addHotelItem);

			JMenuItem updateHotelItem = new JMenuItem("更改酒店信息");
			updateHotelItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateHotelInfo();
				}
			});
			adminMenu.add(updateHotelItem);

			JMenuItem addRoomItem = new JMenuItem("录入房间信息");
			addRoomItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					AddRoomView addRoomView = new AddRoomView();
					addRoomView.setVisible(true);
				}
			});
			adminMenu.add(addRoomItem);

			JMenuItem updateRoomItem = new JMenuItem("更改房间信息");
			updateRoomItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateRoomInfo();
				}
			});
			adminMenu.add(updateRoomItem);

			JMenuItem deleteRoomItem = new JMenuItem("删除房间");
			deleteRoomItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					deleteRoom();
				}
			});
			adminMenu.add(deleteRoomItem);

			JMenuItem viewRoomCountItem = new JMenuItem("查看酒店房间数");
			viewRoomCountItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					viewRoomCount();
				}
			});
			adminMenu.add(viewRoomCountItem);
		}

		// 用户操作菜单（对所有用户显示）
		JMenu userMenu = new JMenu("用户操作");
		menuBar.add(userMenu);

		JMenuItem bookRoomItem = new JMenuItem("预订房间");
		bookRoomItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookRoomView bookRoomView = new BookRoomView();
				bookRoomView.setVisible(true);
			}
		});
		userMenu.add(bookRoomItem);

		JMenuItem viewOrdersItem = new JMenuItem("查看我的订单");
		viewOrdersItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewMyOrders();
			}
		});
		userMenu.add(viewOrdersItem);

		// 退房菜单
		JMenuItem checkoutItem = new JMenuItem("退房");
		checkoutItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				checkout();
			}
		});
		userMenu.add(checkoutItem);

		// 查询面板
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new TitledBorder("酒店基础信息操作"));
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		searchField = new JTextField(20);
		searchPanel.add(searchField);

		JButton searchButton = new JButton("查询(酒店id)");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchHotels();
			}
		});
		searchPanel.add(searchButton);

		frame.add(searchPanel, BorderLayout.NORTH);

		// 酒店信息面板
		JPanel hotelPanel = new JPanel();
		hotelPanel.setBorder(new TitledBorder("酒店信息"));
		hotelPanel.setLayout(new BorderLayout());
		hotelPanel.setPreferredSize(new Dimension(800, 250)); // 设置固定高度

		hotelTable = new JTable();
		JScrollPane hotelScrollPane = new JScrollPane(hotelTable);
		hotelPanel.add(hotelScrollPane, BorderLayout.CENTER);

		frame.add(hotelPanel, BorderLayout.CENTER);

		// 房间信息面板
		JPanel roomPanel = new JPanel();
		roomPanel.setBorder(new TitledBorder("房间信息"));
		roomPanel.setLayout(new BorderLayout());
		roomPanel.setPreferredSize(new Dimension(800, 250)); // 设置固定高度

		roomTable = new JTable();
		JScrollPane roomScrollPane = new JScrollPane(roomTable);
		roomPanel.add(roomScrollPane, BorderLayout.CENTER);

		frame.add(roomPanel, BorderLayout.SOUTH);

		// 监听酒店表格的选择事件
		hotelTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = hotelTable.getSelectedRow();
					if (selectedRow != -1) {
						String hotelId = (String) hotelTable.getValueAt(selectedRow, 0); // 获取酒店ID
						showRoomsByHotelId(hotelId); // 显示该酒店的房间信息
					}
				}
			}
		});
	}

	private void updateHotelInfo() {
		JTextField hotelIdField = new JTextField();
		JTextField hotelNameField = new JTextField();
		JTextField hotelAddressField = new JTextField();
		JTextField hotelTelField = new JTextField();
		JTextField hotelRatingField = new JTextField();

		Object[] message = {
				"酒店ID:", hotelIdField,
				"酒店名称:", hotelNameField,
				"酒店地址:", hotelAddressField,
				"联系电话:", hotelTelField,
				"酒店评分:", hotelRatingField
		};

		int option = JOptionPane.showConfirmDialog(frame, message, "更改酒店信息", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			Hotel hotel = new Hotel(
					hotelIdField.getText(),
					hotelNameField.getText(),
					hotelAddressField.getText(),
					hotelTelField.getText(),
					hotelRatingField.getText()
			);

			boolean isSuccess = HotelDao.updateHotel(hotel);
			if (isSuccess) {
				JOptionPane.showMessageDialog(frame, "酒店信息更新成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "酒店信息更新失败！", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void updateRoomInfo() {
		JTextField roomIdField = new JTextField();
		JTextField hotelIdField = new JTextField();
		JTextField roomTypeField = new JTextField();
		JTextField priceField = new JTextField();
		JTextField statusField = new JTextField();
		JTextField capacityField = new JTextField();

		Object[] message = {
				"房间ID:", roomIdField,
				"酒店ID:", hotelIdField,
				"房间类型:", roomTypeField,
				"价格:", priceField,
				"当前状态:", statusField,
				"可住人数:", capacityField
		};

		int option = JOptionPane.showConfirmDialog(frame, message, "更改房间信息", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			Room room = new Room(
					roomIdField.getText(),
					hotelIdField.getText(),
					roomTypeField.getText(),
					Double.parseDouble(priceField.getText()),
					statusField.getText(),
					Integer.parseInt(capacityField.getText())
			);

			boolean isSuccess = RoomDao.updateRoom(room);
			if (isSuccess) {
				JOptionPane.showMessageDialog(frame, "房间信息更新成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "房间信息更新失败！", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void searchHotels() {
		String keyword = searchField.getText();
		List<Hotel> hotels = HotelDao.searchHotels(keyword);

		DefaultTableModel hotelModel = new DefaultTableModel();
		hotelModel.addColumn("酒店ID");
		hotelModel.addColumn("酒店名称");
		hotelModel.addColumn("酒店地址");
		hotelModel.addColumn("联系电话");
		hotelModel.addColumn("酒店评分");

		for (Hotel hotel : hotels) {
			hotelModel.addRow(new Object[]{
					hotel.getHotelId(),
					hotel.getHotelName(),
					hotel.getHotelAddress(),
					hotel.getHotelTel(),
					hotel.getHotelRating()
			});
		}
		hotelTable.setModel(hotelModel);
	}

	private void showRoomsByHotelId(String hotelId) {
		List<Room> rooms = RoomDao.getRoomsByHotelId(hotelId);

		DefaultTableModel roomModel = new DefaultTableModel();
		roomModel.addColumn("房间ID");
		roomModel.addColumn("酒店ID");
		roomModel.addColumn("房间类型");
		roomModel.addColumn("价格");
		roomModel.addColumn("当前状态");
		roomModel.addColumn("可住人数");

		for (Room room : rooms) {
			roomModel.addRow(new Object[]{
					room.getRoomId(),
					room.getHotelId(),
					room.getRoomType(),
					room.getPrice(),
					room.getStatus(),
					room.getCapacity()
			});
		}
		roomTable.setModel(roomModel);
	}

	private void viewMyOrders() {
		// 获取当前登录用户的ID
		String userId = LoginView.currentUserId;

		// 查询当前用户的订单信息
		List<Book> books = BookDao.getBooksByUserId(userId);
		if (books.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "您没有预订任何房间！", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// 创建表格模型
		DefaultTableModel orderModel = new DefaultTableModel();
		orderModel.addColumn("订单ID");
		orderModel.addColumn("酒店名称");
		orderModel.addColumn("房间类型");
		orderModel.addColumn("入住时间");
		orderModel.addColumn("退房时间");

		for (Book book : books) {
			Hotel hotel = HotelDao.getHotelById(book.getHotelId());
			Room room = RoomDao.getRoomById(book.getRoomId());
			if (hotel != null && room != null) {
				orderModel.addRow(new Object[]{
						book.getBookId(),
						hotel.getHotelName(),
						room.getRoomType(),
						book.getCheckInDate(),
						book.getCheckOutDate()
				});
			}
		}

		// 创建并显示订单信息窗口
		JFrame orderFrame = new JFrame("我的订单");
		orderFrame.setSize(800, 400);
		orderFrame.setLocationRelativeTo(frame);

		JTable orderTable = new JTable(orderModel);
		JScrollPane scrollPane = new JScrollPane(orderTable);
		orderFrame.add(scrollPane);

		orderFrame.setVisible(true);
	}

	private void deleteRoom() {
		String roomId = JOptionPane.showInputDialog(frame, "请输入要删除的房间ID:");
		if (roomId != null && !roomId.isEmpty()) {
			boolean isSuccess = RoomDao.deleteRoom(roomId);
			if (isSuccess) {
				JOptionPane.showMessageDialog(frame, "房间删除成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "房间删除失败！", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void viewRoomCount() {
		String hotelId = JOptionPane.showInputDialog(frame, "请输入酒店ID:");
		if (hotelId != null && !hotelId.isEmpty()) {
			int roomCount = HotelDao.getRoomCountByHotelId(hotelId);
			if (roomCount >= 0) {
				JOptionPane.showMessageDialog(frame, "酒店 " + hotelId + " 的房间数为: " + roomCount, "房间数", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "获取房间数失败！", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * 用户退房
	 */
	private void checkout() {
		// 1. 获取当前登录用户的ID
		String userId = LoginView.currentUserId;
		if (userId == null) {
			JOptionPane.showMessageDialog(frame, "用户未登录！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// 2. 查询当前用户的所有订单
		List<Book> books = BookDao.getBooksByUserId(userId);
		if (books.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "您没有可退房的订单！", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// 3. 显示订单列表，让用户选择退房的订单
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("订单ID");
		model.addColumn("房间ID");
		model.addColumn("酒店名称");
		model.addColumn("房间类型");
		model.addColumn("入住时间");
		model.addColumn("退房时间");


		for (Book book : books) {
			Hotel hotel = HotelDao.getHotelById(book.getHotelId());
			Room room = RoomDao.getRoomById(book.getRoomId());
			if (hotel != null && room != null) {
				model.addRow(new Object[]{
						book.getBookId(),
						room.getRoomId(),
						hotel.getHotelName(),
						room.getRoomType(),
						book.getCheckInDate(),
						book.getCheckOutDate()
				});
			}
		}

		JTable orderTable = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(orderTable);
		int option = JOptionPane.showConfirmDialog(frame, scrollPane, "选择要退房的订单", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			int selectedRow = orderTable.getSelectedRow();
			if (selectedRow != -1) {
				String bookId = (String) orderTable.getValueAt(selectedRow, 0);

				// 4. 执行退房操作
				boolean isSuccess = BookDao.cancelBook(bookId);
				if (isSuccess) {
					JOptionPane.showMessageDialog(frame, "退房成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "退房失败！", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}