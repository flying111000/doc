package com.statictool.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

import HotelBeds.ImportData;

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class start {

	private JFrame frmStaticdataimportguitool;
	private JTextField textField;
	private static PrintStream ps = null; 
	JScrollPane scrollPane = new JScrollPane();
	AdjustmentListener myListenerner;
	
	private DataRunnable dr;
	private final Action action = new SwingAction();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					start window = new start();
					window.frmStaticdataimportguitool.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public start() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmStaticdataimportguitool = new JFrame();
		frmStaticdataimportguitool.setResizable(false);
		frmStaticdataimportguitool.setTitle("StaticDataImportGuiTool");
		frmStaticdataimportguitool.setBounds(100, 100, 935, 695);
		frmStaticdataimportguitool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmStaticdataimportguitool.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		scrollPane.setBounds(20, 101, 899, 555);
		panel.add(scrollPane);
		
		myListenerner=new AdjustmentListener() {  
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }

		};
		
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(299, 36, 122, 24);
		comboBox.addItem("Please Select"); 
//		comboBox.addItem("Country"); 
//		comboBox.addItem("City"); 
//		comboBox.addItem("Destination"); 
//		comboBox.addItem("Hotel"); 
		comboBox.addItem("Room"); 
		comboBox.addItem("HotelRoom"); 
		comboBox.addItem("Board"); 
		comboBox.addItem("Facility");
		comboBox.addItem("RoomFacility");
		panel.add(comboBox);
		
		JComboBox comboBox_provider = new JComboBox();
		comboBox_provider.setBounds(97, 36, 128, 24);
		comboBox_provider.addItem("Please Select"); 
		comboBox_provider.addItem("Hotelbeds"); 
		comboBox_provider.addItem("Innstant"); 
		panel.add(comboBox_provider);

		JLabel lblDataType = new JLabel("Data Type:");
		lblDataType.setBounds(235, 37, 72, 22);
		panel.add(lblDataType);
		
		textField = new JTextField();
		textField.setBounds(540, 36, 169, 23);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblDataTableName = new JLabel("Data Table Name:");
		lblDataTableName.setBounds(431, 36, 110, 22);
		panel.add(lblDataTableName);
//		textArea.setBounds(26, 104, 861, 534);
//		panel.add(textArea);

	    ps = new PrintStream(System.out,true) { 
		        public void println(String x) { 
		        	textArea.append(x + "\n"); 
		        } 
		        public void println(Object o){ 
		        	textArea.append(o + "\n"); 
		        }
	    }; 
	    System.setOut(ps);
	    System.setErr(ps);
	    
		
		JLabel lblOutput = new JLabel("Output:");
		lblOutput.setBounds(26, 71, 72, 22);
		panel.add(lblOutput);
		
		JLabel lblProvider = new JLabel("Provider:");
		lblProvider.setBounds(26, 41, 61, 14);
		panel.add(lblProvider);
		
		JCheckBox chckbxCreateTable = new JCheckBox("Create Table");
		chckbxCreateTable.setBounds(715, 37, 97, 23);
		panel.add(chckbxCreateTable);
		
		JButton btnImport = new JButton("Import");
		btnImport.setBounds(818, 37, 89, 23);
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				for(int i=0;i<10000;i++){
//					System.out.println(comboBox.getSelectedItem().toString());
//					System.out.println(textField.getText());
//				}
				dataImp(comboBox_provider.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),textField.getText(),chckbxCreateTable.isSelected());
			}
		});
		panel.add(btnImport);
		
		JCheckBox chckbxPause = new JCheckBox("Pause");
		chckbxPause.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(null==dr) return;
				try {
					System.out.println(chckbxPause.isSelected());
					if(chckbxPause.isSelected()){
						dr.suspend();
					}else{
						dr.resume();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		chckbxPause.setBounds(540, 66, 97, 23);
		panel.add(chckbxPause);
		
		JCheckBox chckbxScroll = new JCheckBox("Scroll");
		chckbxScroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(chckbxScroll.isSelected()){
						scrollPane.getVerticalScrollBar().addAdjustmentListener(myListenerner);
					}else{
						scrollPane.getVerticalScrollBar().removeAdjustmentListener(myListenerner);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		chckbxScroll.setSelected(true);
		chckbxScroll.setBounds(655, 66, 97, 23);
		panel.add(chckbxScroll);
	}
	
	public class DataRunnable extends Thread {
		public void run() {
			scrollPane.getVerticalScrollBar().addAdjustmentListener(myListenerner);
			
			System.out.println("Begin at :"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			System.out.println("Provider is :"+provider);
			System.out.println("Data type is :"+type);
			
			if("Hotelbeds".equals(provider)){
				if("Room".equals(type)){
					ImportData.importRoom(tableNm,createTable);
				}
				if("HotelRoom".equals(type)){
					ImportData.importHotelRoom(tableNm,createTable);
				}
				if("Board".equals(type)){
					ImportData.importBoard(tableNm,createTable);
				}
				if("Facility".equals(type)){
					ImportData.importFacility(tableNm,createTable);
				}
				if("RoomFacility".equals(type)){
					ImportData.importRoomFacility(tableNm,createTable);
				}
			}
			
			if("Innstant".equals(provider)){
				if("Facility".equals(type)){
					Innstant.ImportData.importFacility(tableNm,createTable);
				}
			}
			
			AdjustmentListener[] ll=scrollPane.getVerticalScrollBar().getAdjustmentListeners();
			for(AdjustmentListener l:ll){
				if(l.equals(myListenerner)){
					scrollPane.getVerticalScrollBar().removeAdjustmentListener(l);
				}
				
			}
			System.out.println("Complete at :"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	    }

		private String provider;
		private String type;
		private String tableNm;
		private boolean createTable=false;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getTableNm() {
			return tableNm;
		}

		public void setTableNm(String tableNm) {
			this.tableNm = tableNm;
		}

		public String getProvider() {
			return provider;
		}

		public void setProvider(String provider) {
			this.provider = provider;
		}

		public boolean isCreateTable() {
			return createTable;
		}

		public void setCreateTable(boolean createTable) {
			this.createTable = createTable;
		}
		

	}
	
	private void dataImp(String provider,String type,String tableNm,boolean createTable){
		if("Please Select".equals(provider)){
			System.out.println("Please select data provider.");
			return;
		}
		if("Please Select".equals(type)){
			System.out.println("Please select data type.");
			return;
		}
		if("".equals(tableNm)||null==tableNm){
			System.out.println("Please type table name.");
			return;
		}
		
		dr=new DataRunnable();
		dr.setProvider(provider);
		dr.setType(type);
		dr.setTableNm(tableNm);
		dr.setCreateTable(createTable);
		
		(new Thread(dr)).start();
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
