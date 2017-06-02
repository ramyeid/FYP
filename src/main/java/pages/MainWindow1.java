import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MainWindow1 extends JFrame
{

	private JPanel contentPane;
	private static MainWindow1 frame;

	private JInternalFrame csvInternalFrame;

	private JTextField pathToCsv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					frame = new MainWindow1();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow1()
	{
		setTitle("Pattern Prediction and Forecasting");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 615);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnFile);

		JMenuItem mntmLoadCsv = new JMenuItem("Load CSV");
		mntmLoadCsv.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				csvInternalFrame.setVisible(true);
			}
		});
		mntmLoadCsv.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnFile.add(mntmLoadCsv);

		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		});
		mntmClose.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnFile.add(mntmClose);

		JMenu mnAlgorithms = new JMenu("Algorithms");
		mnAlgorithms.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnAlgorithms);
		mnAlgorithms.setEnabled(false);

		JMenuItem mntmNeurolNetwork = new JMenuItem("Neural Network");
		mntmNeurolNetwork.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAlgorithms.add(mntmNeurolNetwork);

		JMenu mnTimeSeriesAnalysis = new JMenu("Time Series Analysis");
		mnTimeSeriesAnalysis.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAlgorithms.add(mnTimeSeriesAnalysis);

		JMenuItem mntmPredict = new JMenuItem("Predict");
		mnTimeSeriesAnalysis.add(mntmPredict);

		JMenuItem mntmForecastOnce = new JMenuItem("Forecast Once");
		mnTimeSeriesAnalysis.add(mntmForecastOnce);

		JMenuItem mntmForecastVsActual = new JMenuItem("Forecast vs Actual");
		mnTimeSeriesAnalysis.add(mntmForecastVsActual);

		JMenuItem mntmContinuousForecast = new JMenuItem("Continuous Forecast");
		mnTimeSeriesAnalysis.add(mntmContinuousForecast);

		JMenuItem mntmNaiveBayes = new JMenuItem("Naive Bayes");
		mntmNaiveBayes.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAlgorithms.add(mntmNaiveBayes);

		JMenuItem mntmBayesianNetwork = new JMenuItem("Bayesian Network");
		mntmBayesianNetwork.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAlgorithms.add(mntmBayesianNetwork);

		JMenuItem mntmSupportVectorMachine = new JMenuItem("Support Vector Machine");
		mntmSupportVectorMachine.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAlgorithms.add(mntmSupportVectorMachine);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null,"This tool is the swiss knife of prediction tools. You will be able to use several predictive algorithms to forecast your data.");
			}
		});
		mntmAbout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnHelp.add(mntmAbout);

		JMenuItem menuItem = new JMenuItem("");
		mnHelp.add(menuItem);

		JMenuItem mntmAboutNeuralNetwork = new JMenuItem("Neural Network");
		mnHelp.add(mntmAboutNeuralNetwork);

		JMenuItem mntmAboutTimeSeriesAnalysis = new JMenuItem("Time Series Analysis");
		mnHelp.add(mntmAboutTimeSeriesAnalysis);

		JMenuItem mntmAboutNaiveBayes = new JMenuItem("Naive Bayes");
		mnHelp.add(mntmAboutNaiveBayes);

		JMenuItem mntmBayesianNetwork_1 = new JMenuItem("Bayesian Network");
		mnHelp.add(mntmBayesianNetwork_1);

		JMenuItem mntmSupportVactorMachine = new JMenuItem("Support Vactor Machine");
		mnHelp.add(mntmSupportVactorMachine);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		csvInternalFrame = new JInternalFrame("Load CSV");
		csvInternalFrame.setBounds(0, 0, 506, 399);
		contentPane.add(csvInternalFrame);
		csvInternalFrame.setVisible(false);
		csvInternalFrame.getContentPane().setLayout(null);
		pathToCsv = new JTextField();
		pathToCsv.setBounds(63, 157, 364, 30);
		csvInternalFrame.getContentPane().add(pathToCsv);
		pathToCsv.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				JFileChooser fileChooser = new JFileChooser();

				// For File
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				fileChooser.setAcceptAllFileFilterUsed(false);

				int rVal = fileChooser.showOpenDialog(null);
				if (rVal == JFileChooser.APPROVE_OPTION)
				{
					pathToCsv.setText(fileChooser.getSelectedFile().toString());
				}
			}
		});
		btnBrowse.setBounds(97, 223, 105, 30);
		csvInternalFrame.getContentPane().add(btnBrowse);

		JLabel lblEnterThePath = new JLabel("Enter the path or browse for the CSV file");
		lblEnterThePath.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterThePath.setBounds(62, 55, 364, 30);
		csvInternalFrame.getContentPane().add(lblEnterThePath);
		csvInternalFrame.setClosable(true);

		JButton btnDoneCsv = new JButton("Done");
		btnDoneCsv.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!pathToCsv.getText().contains(".csv"))
				{
					JOptionPane.showMessageDialog(null,"Please choose a file with a .csv extension");
				}

				else
				{
					String csvPath = pathToCsv.getText();
					mnAlgorithms.setEnabled(true);
					csvInternalFrame.dispose();
				}

			}
		});
		btnDoneCsv.setBounds(292, 223, 105, 30);
		csvInternalFrame.getContentPane().add(btnDoneCsv);
	}
}
