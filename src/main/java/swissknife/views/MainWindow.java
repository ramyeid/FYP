package swissknife.views;

import swissknife.views.linearregression.LinearRegressionForecastVsActual;
import swissknife.views.linearregression.LinearRegressionPredict;
import swissknife.views.timeseriesanalysis.TimeSeriesContinuousForecast;
import swissknife.views.timeseriesanalysis.TimeSeriesForecastOnce;
import swissknife.views.timeseriesanalysis.TimeSeriesForecastVsActual;
import swissknife.views.timeseriesanalysis.TimeSeriesPredict;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//TODO PACK LOAD CSV
//TODO BROWSE DIRECTLY NO PATH
//TODO MENU WRITING SMALLER
//TODO READ EXCEL FILES NOT JUST CSV
//TODO PANEL DESIGNS TO BE FIXED
//TODO OPEN INTERNAL FRAME IN CENTER
//TODO Graph in new internal frame
//TODO title for internal frames
//TODO bring to front new internal

public class MainWindow extends JFrame
{
	private JPanel contentPane;
	private static MainWindow frame;

	private JInternalFrame csvInternalFrame;

	private JTextField pathToCsv;

	private String csvPath;

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
					frame = new MainWindow();
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
	public MainWindow()
	{
		setTitle("Pattern Prediction and Forecasting");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 615);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

//*****************
//*** MENU FILE ***
//*****************

		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnFile);

//Load CSV

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

//Close

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

//***********************
//*** MENU ALGORITHMS ***
//***********************

		JMenu mnAlgorithms = new JMenu("Algorithms");
		mnAlgorithms.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnAlgorithms);
		mnAlgorithms.setEnabled(false);

// Menu Neural Network

		JMenuItem mntmNeurolNetwork = new JMenuItem("Neural Network");
		mntmNeurolNetwork.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		mntmNeurolNetwork.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAlgorithms.add(mntmNeurolNetwork);

//Menu Time Series Analysis

		JMenu mnTimeSeriesAnalysis = new JMenu("Time Series Analysis");
		mnTimeSeriesAnalysis.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAlgorithms.add(mnTimeSeriesAnalysis);

		//Prediction
		JMenuItem mntmPredict = new JMenuItem("Prediction Only");
		mntmPredict.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TimeSeriesPredict timeSeriesPredictInternalFrame = new TimeSeriesPredict(csvPath,MainWindow.this);
				frame.add(timeSeriesPredictInternalFrame);
				timeSeriesPredictInternalFrame.setVisible(true);
				timeSeriesPredictInternalFrame.pack();
				timeSeriesPredictInternalFrame.setClosable(true);
			}
		});
		mnTimeSeriesAnalysis.add(mntmPredict);

		//Forecast Once
		JMenuItem mntmForecastOnce = new JMenuItem("Forecast Once");
		mntmForecastOnce.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TimeSeriesForecastOnce timeSeriesForecastOnceInternalFrame = new TimeSeriesForecastOnce(csvPath,MainWindow.this);
				frame.add(timeSeriesForecastOnceInternalFrame);
				timeSeriesForecastOnceInternalFrame.setVisible(true);
				timeSeriesForecastOnceInternalFrame.pack();
				timeSeriesForecastOnceInternalFrame.setClosable(true);
			}
		});
		mnTimeSeriesAnalysis.add(mntmForecastOnce);

		//Forecast Vs Actual (TSA)
		JMenuItem mntmForecastVsActual = new JMenuItem("Forecast vs Actual");
		mntmForecastVsActual.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TimeSeriesForecastVsActual timeSeriesForecastVsActualInternalFrame = new TimeSeriesForecastVsActual(csvPath,MainWindow.this);
				frame.add(timeSeriesForecastVsActualInternalFrame);
				timeSeriesForecastVsActualInternalFrame.setVisible(true);
				timeSeriesForecastVsActualInternalFrame.pack();
				timeSeriesForecastVsActualInternalFrame.setClosable(true);
			}
		});
		mnTimeSeriesAnalysis.add(mntmForecastVsActual);

		//Continous Forecast
		JMenuItem mntmContinuousForecast = new JMenuItem("Continuous Forecast");
		mntmContinuousForecast.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TimeSeriesContinuousForecast timeSeriesContinuousForecastInternalFrame = new TimeSeriesContinuousForecast(csvPath,MainWindow.this);
				frame.add(timeSeriesContinuousForecastInternalFrame);
				timeSeriesContinuousForecastInternalFrame.setVisible(true);
				timeSeriesContinuousForecastInternalFrame.pack();
				timeSeriesContinuousForecastInternalFrame.setClosable(true);
			}
		});
		mnTimeSeriesAnalysis.add(mntmContinuousForecast);

//Menu Linear Regression

		JMenu mnLinearRegression = new JMenu("Linear Regression");
		mnLinearRegression.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAlgorithms.add(mnLinearRegression);

		//Prediction only (LR)
		JMenuItem mntmLRPredict = new JMenuItem("Prediction Only");
		mntmLRPredict.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				LinearRegressionPredict linearRegressionPredictInternalFrame = new LinearRegressionPredict(csvPath);
				frame.add(linearRegressionPredictInternalFrame);
				linearRegressionPredictInternalFrame.setVisible(true);
				linearRegressionPredictInternalFrame.pack();
				linearRegressionPredictInternalFrame.setClosable(true);
			}
		});
		mnLinearRegression.add(mntmLRPredict);

		//Forecast Vs Actual (LR)
		JMenuItem mntmLRForecastVsActual = new JMenuItem("Forecast Vs Actual");
		mntmLRForecastVsActual.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				LinearRegressionForecastVsActual linearRegressionForecastVsActualInternalFrame = new LinearRegressionForecastVsActual(csvPath);
				frame.add(linearRegressionForecastVsActualInternalFrame);

				linearRegressionForecastVsActualInternalFrame.setVisible(true);
				linearRegressionForecastVsActualInternalFrame.pack();
				linearRegressionForecastVsActualInternalFrame.setClosable(true);
			}
		});
		mnLinearRegression.add(mntmLRForecastVsActual);

		//Menu Naive Bayes

		JMenu mnNaiveBayes = new JMenu("Naive Bayes");
		mnNaiveBayes.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAlgorithms.add(mnNaiveBayes);

		//Prediction only
		JMenuItem mntmNBPredict = new JMenuItem("Prediction Only");
		mntmNBPredict.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
//				NaiveBayesPredict naiveBayesPredictInternalFrame = new NaiveBayesPredict(csvPath);
//				frame.add(naiveBayesPredictInternalFrame);
//				naiveBayesPredictInternalFrame.setVisible(true);
//				naiveBayesPredictInternalFrame.pack();
//				naiveBayesPredictInternalFrame.setClosable(true);


			}
		});
		mnNaiveBayes.add(mntmNBPredict);

		//Forecast Vs Actual (NB)
		JMenuItem mntmNBForecastVsActual = new JMenuItem("Forecast Vs Actual");
		mntmNBForecastVsActual.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
//				NaiveBayesForecastVsActual naiveBayesForecastVsActualInternalFrame = new NaiveBayesForecastVsActual(csvPath);
//				frame.add(naiveBayesForecastVsActualInternalFrame);
//
//				naiveBayesForecastVsActualInternalFrame.setVisible(true);
//				naiveBayesForecastVsActualInternalFrame.pack();
//				naiveBayesForecastVsActualInternalFrame.setClosable(true);
			}
		});
		mnNaiveBayes.add(mntmNBForecastVsActual);


//Bayesian Network

		JMenuItem mntmBayesianNetwork = new JMenuItem("Bayesian Network");
		mntmBayesianNetwork.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		mntmBayesianNetwork.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAlgorithms.add(mntmBayesianNetwork);

//Suport Vector Machine

		JMenuItem mntmSupportVectorMachine = new JMenuItem("Support Vector Machine");
		mntmSupportVectorMachine.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		mntmSupportVectorMachine.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAlgorithms.add(mntmSupportVectorMachine);

//*****************
//*** MENU HELP ***
//*****************

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
					csvPath = pathToCsv.getText();
					mnAlgorithms.setEnabled(true);
					csvInternalFrame.dispose();
				}

			}
		});
		btnDoneCsv.setBounds(292, 223, 105, 30);
		csvInternalFrame.getContentPane().add(btnDoneCsv);
	}
}
