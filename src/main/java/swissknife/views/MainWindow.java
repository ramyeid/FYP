package swissknife.views;

import swissknife.views.extratree.ExtraTreeForecastVsActual;
import swissknife.views.gradientboosting.GradientBoostingForecastVsActual;
import swissknife.views.gradientboosting.GradientBoostingPredict;
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

//
////TODO PACK LOAD CSV
////TODO BROWSE DIRECTLY NO PATH
////TODO MENU WRITING SMALLER
////TODO READ EXCEL FILES NOT JUST CSV
////TODO PANEL DESIGNS TO BE FIXED
////TODO OPEN INTERNAL FRAME IN CENTER
////TODO Graph in new internal frame
////TODO title for internal frames
////TODO bring to front new internal
//
//public class MainWindow extends JFrame
//{
//	private JPanel contentPane;
//	private static MainWindow frame;
//
//	private JInternalFrame csvInternalFrame;
//
//	private JTextField pathToCsv;
//
//	private String csvPath;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args)
//	{
//		EventQueue.invokeLater(new Runnable()
//		{
//			public void run()
//			{
//				try
//				{
//					frame = new MainWindow();
//					frame.setVisible(true);
//					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//				}
//				catch (Exception e)
//				{
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public MainWindow()
//	{
//		setTitle("Pattern Prediction and Forecasting");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 834, 615);
//
//		JMenuBar menuBar = new JMenuBar();
//		setJMenuBar(menuBar);
//
////*****************
////*** MENU FILE ***
////*****************
//
//		JMenu mnFile = new JMenu("File");
//		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		menuBar.add(mnFile);
//
////Load CSV
//
//		JMenuItem mntmLoadCsv = new JMenuItem("Load CSV");
//		mntmLoadCsv.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				csvInternalFrame.setVisible(true);
//			}
//		});
//		mntmLoadCsv.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		mnFile.add(mntmLoadCsv);
//
////Close
//
//		JMenuItem mntmClose = new JMenuItem("Close");
//		mntmClose.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				frame.dispose();
//			}
//		});
//		mntmClose.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		mnFile.add(mntmClose);
//
////***********************
////*** MENU ALGORITHMS ***
////***********************
//
//		JMenu mnAlgorithms = new JMenu("Algorithms");
//		mnAlgorithms.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		menuBar.add(mnAlgorithms);
//		mnAlgorithms.setEnabled(false);
//
//// Menu Neural Network
//
//		JMenuItem mntmNeurolNetwork = new JMenuItem("Neural Network");
//		mntmNeurolNetwork.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//			}
//		});
//		mntmNeurolNetwork.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		mnAlgorithms.add(mntmNeurolNetwork);
//
////Menu Time Series Analysis
//
//		JMenu mnTimeSeriesAnalysis = new JMenu("Time Series Analysis");
//		mnTimeSeriesAnalysis.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		mnAlgorithms.add(mnTimeSeriesAnalysis);
//
//		//Prediction
//		JMenuItem mntmPredict = new JMenuItem("Prediction Only");
//		mntmPredict.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				TimeSeriesPredict timeSeriesPredictInternalFrame = new TimeSeriesPredict(csvPath,MainWindow.this);
//				frame.add(timeSeriesPredictInternalFrame);
//				timeSeriesPredictInternalFrame.setVisible(true);
//				timeSeriesPredictInternalFrame.pack();
//				timeSeriesPredictInternalFrame.setClosable(true);
//			}
//		});
//		mnTimeSeriesAnalysis.add(mntmPredict);
//
//		//Forecast Once
//		JMenuItem mntmForecastOnce = new JMenuItem("Forecast Once");
//		mntmForecastOnce.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				TimeSeriesForecastOnce timeSeriesForecastOnceInternalFrame = new TimeSeriesForecastOnce(csvPath,MainWindow.this);
//				frame.add(timeSeriesForecastOnceInternalFrame);
//				timeSeriesForecastOnceInternalFrame.setVisible(true);
//				timeSeriesForecastOnceInternalFrame.pack();
//				timeSeriesForecastOnceInternalFrame.setClosable(true);
//			}
//		});
//		mnTimeSeriesAnalysis.add(mntmForecastOnce);
//
//		//Forecast Vs Actual (TSA)
//		JMenuItem mntmForecastVsActual = new JMenuItem("Forecast vs Actual");
//		mntmForecastVsActual.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				TimeSeriesForecastVsActual timeSeriesForecastVsActualInternalFrame = new TimeSeriesForecastVsActual(csvPath,MainWindow.this);
//				frame.add(timeSeriesForecastVsActualInternalFrame);
//				timeSeriesForecastVsActualInternalFrame.setVisible(true);
//				timeSeriesForecastVsActualInternalFrame.pack();
//				timeSeriesForecastVsActualInternalFrame.setClosable(true);
//			}
//		});
//		mnTimeSeriesAnalysis.add(mntmForecastVsActual);
//
//		//Continous Forecast
//		JMenuItem mntmContinuousForecast = new JMenuItem("Continuous Forecast");
//		mntmContinuousForecast.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				TimeSeriesContinuousForecast timeSeriesContinuousForecastInternalFrame = new TimeSeriesContinuousForecast(csvPath,MainWindow.this);
//				frame.add(timeSeriesContinuousForecastInternalFrame);
//				timeSeriesContinuousForecastInternalFrame.setVisible(true);
//				timeSeriesContinuousForecastInternalFrame.pack();
//				timeSeriesContinuousForecastInternalFrame.setClosable(true);
//			}
//		});
//		mnTimeSeriesAnalysis.add(mntmContinuousForecast);
//
////Menu Linear Regression
//
//		JMenu mnLinearRegression = new JMenu("Linear Regression");
//		mnLinearRegression.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		mnAlgorithms.add(mnLinearRegression);
//
//		//Prediction only (LR)
//		JMenuItem mntmLRPredict = new JMenuItem("Prediction Only");
//		mntmLRPredict.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				LinearRegressionPredict linearRegressionPredictInternalFrame = new LinearRegressionPredict(csvPath);
//				frame.add(linearRegressionPredictInternalFrame);
//				linearRegressionPredictInternalFrame.setVisible(true);
//				linearRegressionPredictInternalFrame.pack();
//				linearRegressionPredictInternalFrame.setClosable(true);
//			}
//		});
//		mnLinearRegression.add(mntmLRPredict);
//
//		//Forecast Vs Actual (LR)
//		JMenuItem mntmLRForecastVsActual = new JMenuItem("Forecast Vs Actual");
//		mntmLRForecastVsActual.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				LinearRegressionForecastVsActual linearRegressionForecastVsActualInternalFrame = new LinearRegressionForecastVsActual(csvPath);
//				frame.add(linearRegressionForecastVsActualInternalFrame);
//
//				linearRegressionForecastVsActualInternalFrame.setVisible(true);
//				linearRegressionForecastVsActualInternalFrame.pack();
//				linearRegressionForecastVsActualInternalFrame.setClosable(true);
//			}
//		});
//		mnLinearRegression.add(mntmLRForecastVsActual);
//
//		//Menu Naive Bayes
//
//		JMenu mnNaiveBayes = new JMenu("Naive Bayes");
//		mnNaiveBayes.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		mnAlgorithms.add(mnNaiveBayes);
//
//		//Prediction only
//		JMenuItem mntmNBPredict = new JMenuItem("Prediction Only");
//		mntmNBPredict.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
////				NaiveBayesPredict naiveBayesPredictInternalFrame = new NaiveBayesPredict(csvPath);
////				frame.add(naiveBayesPredictInternalFrame);
////				naiveBayesPredictInternalFrame.setVisible(true);
////				naiveBayesPredictInternalFrame.pack();
////				naiveBayesPredictInternalFrame.setClosable(true);
//
//
//			}
//		});
//		mnNaiveBayes.add(mntmNBPredict);
//
//		//Forecast Vs Actual (NB)
//		JMenuItem mntmNBForecastVsActual = new JMenuItem("Forecast Vs Actual");
//		mntmNBForecastVsActual.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
////				NaiveBayesForecastVsActual naiveBayesForecastVsActualInternalFrame = new NaiveBayesForecastVsActual(csvPath);
////				frame.add(naiveBayesForecastVsActualInternalFrame);
////
////				naiveBayesForecastVsActualInternalFrame.setVisible(true);
////				naiveBayesForecastVsActualInternalFrame.pack();
////				naiveBayesForecastVsActualInternalFrame.setClosable(true);
//			}
//		});
//		mnNaiveBayes.add(mntmNBForecastVsActual);
//
//
////Bayesian Network
//
//		JMenuItem mntmBayesianNetwork = new JMenuItem("Bayesian Network");
//		mntmBayesianNetwork.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//			}
//		});
//		mntmBayesianNetwork.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		mnAlgorithms.add(mntmBayesianNetwork);
//
////Suport Vector Machine
//
//		JMenuItem mntmSupportVectorMachine = new JMenuItem("Support Vector Machine");
//		mntmSupportVectorMachine.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//			}
//		});
//		mntmSupportVectorMachine.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		mnAlgorithms.add(mntmSupportVectorMachine);
//
////*****************
////*** MENU HELP ***
////*****************
//
//		JMenu mnHelp = new JMenu("Help");
//		mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		menuBar.add(mnHelp);
//
//		JMenuItem mntmAbout = new JMenuItem("About");
//		mntmAbout.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				JOptionPane.showMessageDialog(null,"This tool is the swiss knife of prediction tools. You will be able to use several predictive algorithms to forecast your data.");
//			}
//		});
//		mntmAbout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		mnHelp.add(mntmAbout);
//
//		JMenuItem menuItem = new JMenuItem("");
//		mnHelp.add(menuItem);
//
//		JMenuItem mntmAboutNeuralNetwork = new JMenuItem("Neural Network");
//		mnHelp.add(mntmAboutNeuralNetwork);
//
//		JMenuItem mntmAboutTimeSeriesAnalysis = new JMenuItem("Time Series Analysis");
//		mnHelp.add(mntmAboutTimeSeriesAnalysis);
//
//		JMenuItem mntmAboutNaiveBayes = new JMenuItem("Naive Bayes");
//		mnHelp.add(mntmAboutNaiveBayes);
//
//		JMenuItem mntmBayesianNetwork_1 = new JMenuItem("Bayesian Network");
//		mnHelp.add(mntmBayesianNetwork_1);
//
//		JMenuItem mntmSupportVactorMachine = new JMenuItem("Support Vactor Machine");
//		mnHelp.add(mntmSupportVactorMachine);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//
//		csvInternalFrame = new JInternalFrame("Load CSV");
//		csvInternalFrame.setBounds(0, 0, 506, 399);
//		contentPane.add(csvInternalFrame);
//		csvInternalFrame.setVisible(false);
//		csvInternalFrame.getContentPane().setLayout(null);
//		pathToCsv = new JTextField();
//		pathToCsv.setBounds(63, 157, 364, 30);
//		csvInternalFrame.getContentPane().add(pathToCsv);
//		pathToCsv.setColumns(10);
//
//		JButton btnBrowse = new JButton("Browse");
//		btnBrowse.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent evt)
//			{
//				JFileChooser fileChooser = new JFileChooser();
//
//				// For File
//				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//
//				fileChooser.setAcceptAllFileFilterUsed(false);
//
//				int rVal = fileChooser.showOpenDialog(null);
//				if (rVal == JFileChooser.APPROVE_OPTION)
//				{
//					pathToCsv.setText(fileChooser.getSelectedFile().toString());
//				}
//			}
//		});
//		btnBrowse.setBounds(97, 223, 105, 30);
//		csvInternalFrame.getContentPane().add(btnBrowse);
//
//		JLabel lblEnterThePath = new JLabel("Enter the path or browse for the CSV file");
//		lblEnterThePath.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblEnterThePath.setBounds(62, 55, 364, 30);
//		csvInternalFrame.getContentPane().add(lblEnterThePath);
//		csvInternalFrame.setClosable(true);
//
//		JButton btnDoneCsv = new JButton("Done");
//		btnDoneCsv.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				if(!pathToCsv.getText().contains(".csv"))
//				{
//					JOptionPane.showMessageDialog(null,"Please choose a file with a .csv extension");
//				}
//
//				else
//				{
//					csvPath = pathToCsv.getText();
//					mnAlgorithms.setEnabled(true);
//					csvInternalFrame.dispose();
//				}
//
//			}
//		});
//		btnDoneCsv.setBounds(292, 223, 105, 30);
//		csvInternalFrame.getContentPane().add(btnDoneCsv);
//	}
//}

/**
 * Created by joeabdelnour on 6/11/17.
 */

import swissknife.views.decisiontree.DecisionTreeForecastVsActual;
import swissknife.views.decisiontree.DecisionTreePredict;
import swissknife.views.extratree.ExtraTreeForecastVsActual;
import swissknife.views.extratree.ExtraTreePredict;
import swissknife.views.gradientboosting.GradientBoostingForecastVsActual;
import swissknife.views.gradientboosting.GradientBoostingPredict;
import swissknife.views.knearestneighbors.KNearestNeighborsForecastVsActual;
import swissknife.views.knearestneighbors.KNearestNeighborsPredict;
import swissknife.views.lineardiscriminant.LinearDiscriminantForecastVsActual;
import swissknife.views.lineardiscriminant.LinearDiscriminantPredict;
import swissknife.views.linearregression.LinearRegressionForecastVsActual;
import swissknife.views.linearregression.LinearRegressionPredict;
import swissknife.views.linearsvm.LinearSvmForecastVsActual;
import swissknife.views.linearsvm.LinearSvmPredict;
import swissknife.views.logisticregression.LogisticRegressionForecastVsActual;
import swissknife.views.logisticregression.LogisticRegressionPredict;
import swissknife.views.naivebayes.NaiveBayesForecastVsActual;
import swissknife.views.naivebayes.NaiveBayesPredict;
import swissknife.views.randomforest.RandomForestForecastVsActual;
import swissknife.views.randomforest.RandomForestPredict;
import swissknife.views.ridge.RidgeForecastVsActual;
import swissknife.views.ridge.RidgePredict;
import swissknife.views.stochasticgradientdescent.StochasticGradientDescentForecastVsActual;
import swissknife.views.stochasticgradientdescent.StochasticGradientDescentPredict;
import swissknife.views.supportvectormachine.SupportVectorMachineForecastVsActual;
import swissknife.views.supportvectormachine.SupportVectorMachinePredict;
import swissknife.views.timeseriesanalysis.TimeSeriesContinuousForecast;
import swissknife.views.timeseriesanalysis.TimeSeriesForecastOnce;
import swissknife.views.timeseriesanalysis.TimeSeriesForecastVsActual;
import swissknife.views.timeseriesanalysis.TimeSeriesPredict;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class MainWindow extends JFrame
{
    private JPanel contentPane;

    private static MainWindow frame;

    private String csvPath;

    private JMenu mnTools;

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
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);


        //*******************
        //*******************
        //**** MENU FILE ****
        //*******************
        //*******************

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);
        JMenuItem mntmLoadCsv = new JMenuItem("Load CSV");
        mntmLoadCsv.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fileChooser = new JFileChooser();
                CsvFilter csv = new CsvFilter();

                // For File
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(csv);
                fileChooser.setAcceptAllFileFilterUsed(false);

                int rVal = fileChooser.showOpenDialog(null);
                if (rVal == JFileChooser.APPROVE_OPTION)
                {
                    csvPath=fileChooser.getSelectedFile().toString();
                }

                if(!csvPath.contains(".csv"))
                {
                    JOptionPane.showMessageDialog(null,"Please choose a file with a .csv extension");
                }

                else
                {
                    mnTools.setEnabled(true);
                }
            }
        });
        mnFile.add(mntmLoadCsv);
        JMenuItem mntmClose = new JMenuItem("Close");
        mntmClose.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
            }
        });
        mnFile.add(mntmClose);



        //********************
        //********************
        //**** MENU TOOLS ****
        //********************
        //********************

        mnTools = new JMenu("Tools");
        menuBar.add(mnTools);
        mnTools.setEnabled(false);


        //***********************
        //**** Decision Tree ****
        //***********************

        JMenu mnDecisionTree = new JMenu("Decision Tree");
        mnTools.add(mnDecisionTree);
        JMenuItem mntmDTPrediction = new JMenuItem("Prediction");
        mntmDTPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                DecisionTreePredict decisionTreePredictInternalFrame = new DecisionTreePredict(csvPath, MainWindow.this);
                frame.add(decisionTreePredictInternalFrame);
                decisionTreePredictInternalFrame.setVisible(true);
                decisionTreePredictInternalFrame.pack();
                decisionTreePredictInternalFrame.setClosable(true);
            }
        });
        mnDecisionTree.add(mntmDTPrediction);
        JMenuItem mntmDTForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmDTForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                DecisionTreeForecastVsActual decisionTreeForecastVsActualInternalFrame = new DecisionTreeForecastVsActual(csvPath, MainWindow.this);
                frame.add(decisionTreeForecastVsActualInternalFrame);
                decisionTreeForecastVsActualInternalFrame.setVisible(true);
                decisionTreeForecastVsActualInternalFrame.pack();
                decisionTreeForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnDecisionTree.add(mntmDTForecastVsActual);

        //********************
        //**** Extra Tree ****
        //********************

        JMenu mnExtraTree = new JMenu("Extra Tree");
        mnTools.add(mnExtraTree);
        JMenuItem mntmETPrediction = new JMenuItem("Prediction");
        mntmETPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ExtraTreePredict extraTreePredictInternalFrame = new ExtraTreePredict(csvPath, MainWindow.this);
                frame.add(extraTreePredictInternalFrame);
                extraTreePredictInternalFrame.setVisible(true);
                extraTreePredictInternalFrame.pack();
                extraTreePredictInternalFrame.setClosable(true);
            }
        });
        mnExtraTree.add(mntmETPrediction);
        JMenuItem mntmETForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmETForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ExtraTreeForecastVsActual extraTreeForecastVsActualInternalFrame = new ExtraTreeForecastVsActual(csvPath, MainWindow.this);
                frame.add(extraTreeForecastVsActualInternalFrame);
                extraTreeForecastVsActualInternalFrame.setVisible(true);
                extraTreeForecastVsActualInternalFrame.pack();
                extraTreeForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnExtraTree.add(mntmETForecastVsActual);

        //***************************
        //**** Gradient Boosting ****
        //***************************

        JMenu mnGradientBoosting = new JMenu("Gradient Boosting");
        mnTools.add(mnGradientBoosting);
        JMenuItem mntmGBPrediction = new JMenuItem("Prediction");
        mntmGBPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                GradientBoostingPredict gradientBoostingPredictInternalFrame = new GradientBoostingPredict(csvPath, MainWindow.this);
                frame.add(gradientBoostingPredictInternalFrame);
                gradientBoostingPredictInternalFrame.setVisible(true);
                gradientBoostingPredictInternalFrame.pack();
                gradientBoostingPredictInternalFrame.setClosable(true);
            }
        });
        mnGradientBoosting.add(mntmGBPrediction);
        JMenuItem mntmGBForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmGBForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                GradientBoostingForecastVsActual gradientBoostingForecastVsActualInternalFrame = new GradientBoostingForecastVsActual(csvPath, MainWindow.this);
                frame.add(gradientBoostingForecastVsActualInternalFrame);
                gradientBoostingForecastVsActualInternalFrame.setVisible(true);
                gradientBoostingForecastVsActualInternalFrame.pack();
                gradientBoostingForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnGradientBoosting.add(mntmGBForecastVsActual);

        //*****************************
        //**** K Nearest Neighbors ****
        //*****************************

        JMenu mnKNearestNeighbors = new JMenu("K Nearest Neighbors");
        mnTools.add(mnKNearestNeighbors);
        JMenuItem mntmKNNPrediction = new JMenuItem("Prediction");
        mntmKNNPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                KNearestNeighborsPredict kNearestNeighborsPredictInternalFrame = new KNearestNeighborsPredict(csvPath, MainWindow.this);
                frame.add(kNearestNeighborsPredictInternalFrame);
                kNearestNeighborsPredictInternalFrame.setVisible(true);
                kNearestNeighborsPredictInternalFrame.pack();
                kNearestNeighborsPredictInternalFrame.setClosable(true);
            }
        });
        mnKNearestNeighbors.add(mntmKNNPrediction);
        JMenuItem mntmKNNForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmKNNForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                KNearestNeighborsForecastVsActual kNearestNeighborsForecastVsActualInternalFrame = new KNearestNeighborsForecastVsActual(csvPath, MainWindow.this);
                frame.add(kNearestNeighborsForecastVsActualInternalFrame);
                kNearestNeighborsForecastVsActualInternalFrame.setVisible(true);
                kNearestNeighborsForecastVsActualInternalFrame.pack();
                kNearestNeighborsForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnKNearestNeighbors.add(mntmKNNForecastVsActual);

        //*****************************
        //**** Linear Discriminant ****
        //*****************************

        JMenu mnLinearDiscriminant = new JMenu("Linear Discriminant");
        mnTools.add(mnLinearDiscriminant);
        JMenuItem mntmLDPrediction = new JMenuItem("Prediction");
        mntmLDPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                LinearDiscriminantPredict linearDiscriminantPredictInternalFrame = new LinearDiscriminantPredict(csvPath, MainWindow.this);
                frame.add(linearDiscriminantPredictInternalFrame);
                linearDiscriminantPredictInternalFrame.setVisible(true);
                linearDiscriminantPredictInternalFrame.pack();
                linearDiscriminantPredictInternalFrame.setClosable(true);
            }
        });
        mnLinearDiscriminant.add(mntmLDPrediction);
        JMenuItem mntmLDForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmLDForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                LinearDiscriminantForecastVsActual linearDiscriminantForecastVsActualInternalFrame = new LinearDiscriminantForecastVsActual(csvPath, MainWindow.this);
                frame.add(linearDiscriminantForecastVsActualInternalFrame);
                linearDiscriminantForecastVsActualInternalFrame.setVisible(true);
                linearDiscriminantForecastVsActualInternalFrame.pack();
                linearDiscriminantForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnLinearDiscriminant.add(mntmLDForecastVsActual);

        //***************************
        //**** Linear Regression ****
        //***************************

        JMenu mnLinearRegression = new JMenu("Linear Regression");
        mnTools.add(mnLinearRegression);
        JMenuItem mntmLiRPrediction = new JMenuItem("Prediction");
        mntmLiRPrediction.addActionListener(new ActionListener()
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
        mnLinearRegression.add(mntmLiRPrediction);
        JMenuItem mntmLiRForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmLiRForecastVsActual.addActionListener(new ActionListener()
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
        mnLinearRegression.add(mntmLiRForecastVsActual);

        //********************
        //**** Linear SVM ****
        //********************

        JMenu mnLinearSvm = new JMenu("Linear SVM");
        mnTools.add(mnLinearSvm);
        JMenuItem mntmLSVMPrediction = new JMenuItem("Prediction");
        mntmLSVMPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                LinearSvmPredict linearSvmPredictInternalFrame = new LinearSvmPredict(csvPath, MainWindow.this);
                frame.add(linearSvmPredictInternalFrame);
                linearSvmPredictInternalFrame.setVisible(true);
                linearSvmPredictInternalFrame.pack();
                linearSvmPredictInternalFrame.setClosable(true);
            }
        });
        mnLinearSvm.add(mntmLSVMPrediction);
        JMenuItem mntmLSVMForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmLSVMForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                LinearSvmForecastVsActual linearSvmForecastVsActualInternalFrame = new LinearSvmForecastVsActual(csvPath, MainWindow.this);
                frame.add(linearSvmForecastVsActualInternalFrame);
                linearSvmForecastVsActualInternalFrame.setVisible(true);
                linearSvmForecastVsActualInternalFrame.pack();
                linearSvmForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnLinearSvm.add(mntmLSVMForecastVsActual);

        //*****************************
        //**** Logistic Regression ****
        //*****************************

        JMenu mnLogisticRegression = new JMenu("Logistic Regression");
        mnTools.add(mnLogisticRegression);
        JMenuItem mntmLoRPrediction = new JMenuItem("Prediction");
        mntmLoRPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                LogisticRegressionPredict logisticRegressionPredictInternalFrame = new LogisticRegressionPredict(csvPath, MainWindow.this);
                frame.add(logisticRegressionPredictInternalFrame);
                logisticRegressionPredictInternalFrame.setVisible(true);
                logisticRegressionPredictInternalFrame.pack();
                logisticRegressionPredictInternalFrame.setClosable(true);
            }
        });
        mnLogisticRegression.add(mntmLoRPrediction);
        JMenuItem mntmLoRForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmLoRForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                LogisticRegressionForecastVsActual logisticRegressionForecastVsActualInternalFrame = new LogisticRegressionForecastVsActual(csvPath, MainWindow.this);
                frame.add(logisticRegressionForecastVsActualInternalFrame);
                logisticRegressionForecastVsActualInternalFrame.setVisible(true);
                logisticRegressionForecastVsActualInternalFrame.pack();
                logisticRegressionForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnLogisticRegression.add(mntmLoRForecastVsActual);

        //*********************
        //**** Naive Bayes ****
        //*********************

        JMenu mnNaiveBayes = new JMenu("Naive Bayes");
        mnTools.add(mnNaiveBayes);
        JMenuItem mntmNBPrediction = new JMenuItem("Prediction");
        mntmNBPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                NaiveBayesPredict naiveBayesPredictInternalFrame = new NaiveBayesPredict(csvPath, MainWindow.this);
                frame.add(naiveBayesPredictInternalFrame);
                naiveBayesPredictInternalFrame.setVisible(true);
                naiveBayesPredictInternalFrame.pack();
                naiveBayesPredictInternalFrame.setClosable(true);
            }
        });
        mnNaiveBayes.add(mntmNBPrediction);
        JMenuItem mntmNBForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmNBForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                NaiveBayesForecastVsActual naiveBayesForecastVsActualInternalFrame = new NaiveBayesForecastVsActual(csvPath, MainWindow.this);
                frame.add(naiveBayesForecastVsActualInternalFrame);
                naiveBayesForecastVsActualInternalFrame.setVisible(true);
                naiveBayesForecastVsActualInternalFrame.pack();
                naiveBayesForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnNaiveBayes.add(mntmNBForecastVsActual);

        //***********************
        //**** Random Forest ****
        //***********************

        JMenu mnRandomForest = new JMenu("Random Forest");
        mnTools.add(mnRandomForest);
        JMenuItem mntmRFPrediction = new JMenuItem("Prediction");
        mntmRFPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                RandomForestPredict randomForestPredictInternalFrame = new RandomForestPredict(csvPath, MainWindow.this);
                frame.add(randomForestPredictInternalFrame);
                randomForestPredictInternalFrame.setVisible(true);
                randomForestPredictInternalFrame.pack();
                randomForestPredictInternalFrame.setClosable(true);
            }
        });
        mnRandomForest.add(mntmRFPrediction);
        JMenuItem mntmRFForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmRFForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                RandomForestForecastVsActual randomForestForecastVsActualInternalFrame = new RandomForestForecastVsActual(csvPath, MainWindow.this);
                frame.add(randomForestForecastVsActualInternalFrame);
                randomForestForecastVsActualInternalFrame.setVisible(true);
                randomForestForecastVsActualInternalFrame.pack();
                randomForestForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnRandomForest.add(mntmRFForecastVsActual);

        //***************
        //**** Ridge ****
        //***************

        JMenu mnRidge = new JMenu("Ridge");
        mnTools.add(mnRidge);
        JMenuItem mntmRidgePredicition = new JMenuItem("Predicition");
        mntmRidgePredicition.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                RidgePredict ridgePredictInternalFrame = new RidgePredict(csvPath, MainWindow.this);
                frame.add(ridgePredictInternalFrame);
                ridgePredictInternalFrame.setVisible(true);
                ridgePredictInternalFrame.pack();
                ridgePredictInternalFrame.setClosable(true);
            }
        });
        mnRidge.add(mntmRidgePredicition);
        JMenuItem mntmRidgeForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmRidgeForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                RidgeForecastVsActual ridgeForecastVsActualInternalFrame = new RidgeForecastVsActual(csvPath, MainWindow.this);
                frame.add(ridgeForecastVsActualInternalFrame);
                ridgeForecastVsActualInternalFrame.setVisible(true);
                ridgeForecastVsActualInternalFrame.pack();
                ridgeForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnRidge.add(mntmRidgeForecastVsActual);

        //*************************************
        //**** Stochastic Gradient Descent ****
        //*************************************

        JMenu mnStochasticGradientDescent = new JMenu("Stochastic Gradient Descent");
        mnTools.add(mnStochasticGradientDescent);
        JMenuItem mntmSGDPrediction = new JMenuItem("Prediction");
        mntmSGDPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                StochasticGradientDescentPredict stochasticGradientDescentPredictInternalFrame = new StochasticGradientDescentPredict(csvPath, MainWindow.this);
                frame.add(stochasticGradientDescentPredictInternalFrame);
                stochasticGradientDescentPredictInternalFrame.setVisible(true);
                stochasticGradientDescentPredictInternalFrame.pack();
                stochasticGradientDescentPredictInternalFrame.setClosable(true);
            }
        });
        mnStochasticGradientDescent.add(mntmSGDPrediction);
        JMenuItem mntmSGDForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmSGDForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                StochasticGradientDescentForecastVsActual stochasticGradientDescentForecastVsActualInternalFrame = new StochasticGradientDescentForecastVsActual(csvPath, MainWindow.this);
                frame.add(stochasticGradientDescentForecastVsActualInternalFrame);
                stochasticGradientDescentForecastVsActualInternalFrame.setVisible(true);
                stochasticGradientDescentForecastVsActualInternalFrame.pack();
                stochasticGradientDescentForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnStochasticGradientDescent.add(mntmSGDForecastVsActual);

        //********************************
        //**** Support Vector Machine ****
        //********************************

        JMenu mnSupportVectotMachine = new JMenu("Support Vector Machine");
        mnTools.add(mnSupportVectotMachine);
        JMenuItem mntmSVMPrediction = new JMenuItem("Prediction");
        mntmSVMPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                SupportVectorMachinePredict supportVectorMachinePredictInternalFrame = new SupportVectorMachinePredict(csvPath, MainWindow.this);
                frame.add(supportVectorMachinePredictInternalFrame);
                supportVectorMachinePredictInternalFrame.setVisible(true);
                supportVectorMachinePredictInternalFrame.pack();
                supportVectorMachinePredictInternalFrame.setClosable(true);
            }
        });
        mnSupportVectotMachine.add(mntmSVMPrediction);
        JMenuItem mntmSVMForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmSVMForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                SupportVectorMachineForecastVsActual supportVectorMachineForecastVsActualInternalFrame = new SupportVectorMachineForecastVsActual(csvPath, MainWindow.this);
                frame.add(supportVectorMachineForecastVsActualInternalFrame);
                supportVectorMachineForecastVsActualInternalFrame.setVisible(true);
                supportVectorMachineForecastVsActualInternalFrame.pack();
                supportVectorMachineForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnSupportVectotMachine.add(mntmSVMForecastVsActual);

        //******************************
        //**** Time Series Analysis ****
        //******************************

        JMenu mnTimeSeriesAnalysis = new JMenu("Time Series Analysis ");
        mnTools.add(mnTimeSeriesAnalysis);
        JMenuItem mntmTSAPrediction = new JMenuItem("Prediction");
        mntmTSAPrediction.addActionListener(new ActionListener()
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
        mnTimeSeriesAnalysis.add(mntmTSAPrediction);
        JMenuItem mntmTSAForecastOnce = new JMenuItem("Forecast Once");
        mntmTSAForecastOnce.addActionListener(new ActionListener()
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
        mnTimeSeriesAnalysis.add(mntmTSAForecastOnce);
        JMenuItem mntmTSAForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmTSAForecastVsActual.addActionListener(new ActionListener()
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
        mnTimeSeriesAnalysis.add(mntmTSAForecastVsActual);
        JMenuItem mntmTSAContinuousForecast = new JMenuItem("Continuous Forecast");
        mntmTSAContinuousForecast.addActionListener(new ActionListener()
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


        //*******************
        //*******************
        //**** MENU HELP ****
        //*******************
        //*******************

        mnTimeSeriesAnalysis.add(mntmTSAContinuousForecast);
        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);
        JMenuItem mntmAbout = new JMenuItem("About");
        mntmAbout.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            }
        });
        mnHelp.add(mntmAbout);



        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
    }
}

class CsvFilter extends FileFilter
{
    @Override
    public boolean accept(File f)
    {
        return f.getName().toLowerCase().endsWith(".csv")||f.isDirectory();
    }
    @Override
    public String getDescription(){
        return "Comma Separated Values (*.csv)";
    }
}
