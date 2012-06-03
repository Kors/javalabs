import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.*;

public class ClientInWindow{

	public static Thread listener;	

	public static void main(String[] args) {

		final AppClient client = new AppClient( 7000, "localhost");

		// создаем фрейм Чата
		final ChatFrame frame = new ChatFrame(client);
		//добавляем WindowListener. здесь просто выход и всё.
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		}); 
		frame.setVisible(true);

		/////////////////////////////// запуск потока, принимающего сообщения от сервера.		
		listener = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Listen( client.getOIS(),frame.textArea );
					//socket.recieveMessa;ges(textArea);
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		});
		listener.start();
		
	}

	protected static void Listen(ObjectInputStream ois,JTextArea textArea) {
		Message localMessage = null;		
		while(true){			
			try {
				localMessage = (Message) ois.readObject();
			} catch (IOException e) {
				//e.printStackTrace();
				try {
					ois.close();
					break;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				//e.printStackTrace();
				try {
					ois.close();
					break;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			// отображаем текст сообщения в окне
			textArea.append(localMessage.toString()+"\n");
		}		
	}
}

class ChatFrame extends JFrame {
	// размеры
	public static final int _width = 350;
	public static final int _height = 400;
	// вспомогательные переменные
	public JTextArea textArea = null;
	private String tempName ="";

	public ChatFrame(final AppClient client) {

		//окно
		setTitle("Kors's Chat");
		setSize(_width, _height);

		// "панель"
		JPanel header = new JPanel();
		header.setLayout(new GridLayout(1, 3));
		header.add(new JLabel("Ваше имя:", SwingConstants.LEFT));
		final JTextField LoginField = new JTextField();
		// кнопка логин
		JButton loginButton = new JButton("Изменить");
		// добавляем это всё в header
		header.add(LoginField);
		//header.add(loginButton);
		

		

		// для текста
		//final JTextArea 
		textArea = new JTextArea(10, 20);
		textArea.setEditable(false); // нельзя вписывать самому
		JScrollPane scroller = new JScrollPane(textArea);
		


		
		JPanel footer = new JPanel();
		footer.setLayout(new GridLayout(2,1));
		final JTextField messageField = new JTextField();
		// отправка сообщений по Enter
		messageField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (client.sendMessage(messageField.getText()))
					messageField.setText("");				
				// TODO Auto-generated method stub
				
			}
		});
		// кнопка send
		JButton sendButton = new JButton("Отправить сообщение");
		// её функции:
		sendButton.addActionListener(new ActionListener() {

			//@Override
			public void actionPerformed(ActionEvent e) {
				if (client.sendMessage(messageField.getText()))
					messageField.setText("");
			}
		});
		footer.add(messageField);
		footer.add(sendButton);
		
		// размещаем
		add(header, BorderLayout.NORTH);
		add(scroller, BorderLayout.CENTER);
		add(footer, BorderLayout.SOUTH);
		
		
	/*	
		// функции логина
		loginButton.addActionListener(new ActionListener() {

			//@Override
			public void actionPerformed(ActionEvent e) {
				if (!client.changeName(LoginField.getText()))
					//LoginField.setText("");
					textArea.append("Can't change name!");
			}
		});*/
		// отправка имени по Enter и перенос фокуса
		LoginField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!LoginField.getText().equals("") & !LoginField.getText().equals(tempName)){
					tempName=LoginField.getText();
					client.changeName(LoginField.getText());
					messageField.requestFocus();
				}
			}
		});	
		
		
		// отправка имени при потере фокуса в окне имени
		LoginField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (!LoginField.getText().equals("") & !LoginField.getText().equals(tempName)){
					tempName=LoginField.getText();
					client.changeName(LoginField.getText());
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub				
			}
		});
	}

}