import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.BorderLayout;


import javax.swing.*;

public class MerchantApp extends JFrame {
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel botPanel;
    JList list;
    private JLabel label;
    private JButton button;
    private DefaultListModel model;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane1;
    Socket socket;
    private String userName;
    private JTextArea chatField;
    private JTextField sendText;
    private int curPos = 1;
    
    private Object[][] row = new Object[20][5];    
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    JTable table;
    DefaultTableModel tempModel;
    String columns[] = {"Current Position" , "User ID", "Status", "Customer Name"};



    public MerchantApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Merchant System");
        this.setSize(800, 1000);

        //panel at the top
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel = new JPanel();
        GridLayout layout = new GridLayout(1, 2, 15, 10);
        botPanel = new JPanel(layout);


        // There should probably be something passed into the JScrollPane

        model = new DefaultListModel();
        list = new JList(model);

//        panelForTextFields.add(scrollPane);
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(botPanel, BorderLayout.SOUTH);
        setVisible(true);

        addToList();
        waitingList();
        chatField();


    }

    private void addToList() {

        JLabel userName = new JLabel("username:");
        JLabel userId = new JLabel("user id:");

        JTextField userName1 = new JTextField(15);
//        JPasswordField passWord1 = new JPasswordField("Type your password")
        JTextField passWord1 = new JTextField(15);
        JButton addButton = new JButton("Add to Waitng List");
        topPanel.add(userName, LEFT_ALIGNMENT);
        topPanel.add(userName1, LEFT_ALIGNMENT);
        topPanel.add(userId, LEFT_ALIGNMENT);
        topPanel.add(passWord1, LEFT_ALIGNMENT);
        topPanel.add(addButton, LEFT_ALIGNMENT);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            	DBdemo DB = new DBdemo();
            	Boolean result = DB.addWaitList(userName1.getText(), Integer.parseInt(passWord1.getText()));
            	if(result) {
            		System.out.print("table size: ");
            		System.out.println(table.getRowCount());
            		Object[] newRow = {table.getRowCount() + 1, Integer.parseInt(passWord1.getText()), "Waiting", userName1.getText()};
            		tempModel.addRow(newRow);
            		table.setModel(tempModel);
            		curPos ++;
            		JOptionPane.showMessageDialog(null, "User added to waiting List",
                            null, JOptionPane.PLAIN_MESSAGE);
            		userName1.setText("");
            		passWord1.setText("");
            		
            	}else {
            		JOptionPane.showMessageDialog(null, "User already in waiting list or no such user",
                            null, JOptionPane.PLAIN_MESSAGE);
            	}
            	
            	
            }
        });
    }


    private void waitingList() {
    	DBdemo DB = new DBdemo();
    	ResultSet result = DB.getWaitList();
    	int count = 0;
    	tempModel = new DefaultTableModel();
        tempModel.setColumnIdentifiers(columns);
    	try {
			while(result.next()) {
				int pos = result.getInt("cur_pos");
				int id = result.getInt("customer_id");
				String cusName = result.getString("customer_name");
				String status = result.getString("status");
				
        		Object[] newRow = {pos, id, status, cusName};
//        		
//				row[count][0] = pos;
//				row[count][1] = id;
//				row[count][2] = cusName;
//				row[count][3] = status;
				System.out.println(result.getInt("cur_pos"));
				count ++;
				curPos ++;
				tempModel.addRow(newRow);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        table = new JTable(tempModel);
        table.setPreferredSize(new Dimension(400,650));
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400,650));
        centerPanel.add(scrollPane);

    }
    
    
    

    private void chatField() {
        chatField = new JTextArea();
        chatField.setCaretPosition(0);
        chatField.setEditable(false);
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        scrollPane1 = new JScrollPane(chatField);
        scrollPane1.setPreferredSize(new Dimension(350, 580));
        chatPanel.add(scrollPane1);
        sendText = new JTextField();
        sendText.setPreferredSize(new Dimension(350, 70));
        chatServer chating = new chatServer();
        chatPanel.add(chating.textField());
        centerPanel.add(chatPanel);
    }
    class chatServer extends JFrame implements Runnable {
        ArrayList<Socket> list = new ArrayList<>();
        private int clientNo = 0;


        public chatServer() {
            Thread t = new Thread(this);
            t.start();

        }

        @Override
        public void run() {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(8000);
                chatField.append("MultiThreadServer started at "
                        + new Date() + '\n');

                while (true) {

                    // Listen for a new connection request
                    socket = serverSocket.accept();

                    
                    DataInputStream inputFromClient = new DataInputStream(
                            socket.getInputStream());
                    userName = inputFromClient.readUTF();
                    
                    
                    // Increment clientNo
                    clientNo++;

                    chatField.append("Starting thread for client " + clientNo +
                            " at " + new Date() + '\n');

                    // Find the client's host name, and IP address
                    InetAddress inetAddress = socket.getInetAddress();
                    chatField.append("Client " + clientNo + "'s host name is "
                            + userName + "\n");
                    chatField.append("Client " + clientNo + "'s IP Address is "
                            + inetAddress.getHostAddress() + "\n");
                    // Create and start a new thread for the connection
                    new Thread(new HandleAClient(socket, clientNo, userName)).start();
                }
            }
            catch(IOException ex) {
                System.err.println(ex);
            }



        }
        class HandleAClient implements Runnable {
            private Socket socket; // A connected socket
            private int clientNum;
            private String clientName;

            /**
             * Construct a thread
             */
            public HandleAClient(Socket socket, int clientNum, String clientName) {
                this.socket = socket;
                this.clientNum = clientNum;
                this.clientName = clientName;
                synchronized (list) {
                    list.add(socket);
                }
            }

            /**
             * Run a thread
             */
            public void run() {
                try {
                    // Create data input and output streams
                    DataInputStream inputFromClient = new DataInputStream(
                            socket.getInputStream());
                    DataOutputStream outputToClient = new DataOutputStream(
                            socket.getOutputStream());
                    // Continuously serve the client
                    while (true) {
                        String message = inputFromClient.readUTF();

                        synchronized (list) {
                            for (Socket x : list) {
                                try {
                                    DataOutputStream out = new DataOutputStream(
                                            x.getOutputStream());
                                    out.writeUTF(this.clientNum + ": " + message);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }

                        chatField.append("message sent by client :" + this.clientName + ": " +
                                message + '\n');

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        // Define the thread class for handling new connection
        public JTextField textField() {
            class TextFieldListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                        toServer = new DataOutputStream(socket.getOutputStream());
                    }
                    catch (IOException ex) {
                        chatField.append(ex.toString() + '\n');
                    }

                    try {

                            String sentMessage = "Merchant: "+ sendText.getText();
                            synchronized (list) {
                                for (Socket x : list) {
                                    try {
                                        DataOutputStream out = new DataOutputStream(
                                                x.getOutputStream());
                                        out.writeUTF(sentMessage);
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                            chatField.append(sentMessage+'\n');
//                            toServer.writeUTF(sentMessage);
                            toServer.flush();
                    }
                    catch (IOException ex) {
                        System.err.println(ex);
                    }
                    sendText.setText(" ");
                }
            }
            sendText.addActionListener(new TextFieldListener());
            return sendText;
        }
    }

    public static void main (String[]args){
        MerchantApp a = new MerchantApp();
        a.setVisible(true);
    }
}