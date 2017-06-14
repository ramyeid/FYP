package swissknife.views;

import swissknife.CSVReader;
import swissknife.panels.comparetools.CompareToolsPanel;
import swissknife.panels.showvalues.ShowValues;
import swissknife.views.bernoullinaivebayes.BernoulliNaiveBayesForecastVsActual;
import swissknife.views.bernoullinaivebayes.BernoulliNaiveBayesPredict;
import swissknife.views.decisiontree.DecisionTreeForecastVsActual;
import swissknife.views.decisiontree.DecisionTreePredict;
import swissknife.views.extratree.ExtraTreeForecastVsActual;
import swissknife.views.extratree.ExtraTreePredict;
import swissknife.views.gaussiannaivebayes.GaussianNaiveBayesForecastVsActual;
import swissknife.views.gaussiannaivebayes.GaussianNaiveBayesPredict;
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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

//
////TODO PACK LOAD CSV
////TODO READ EXCEL FILES NOT JUST CSV
////TODO PANEL DESIGNS TO BE FIXED
////TODO Graph in new internal frame


public class MainWindow extends JFrame
{
    private JPanel contentPane;

    private static MainWindow frame;

    private String csvPath;

    private JMenu mnTools;
    private JMenu mnComparison;
    private JMenuItem mnShowInputCSV;

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
        mnFile.add(mntmLoadCsv);




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
                    mnComparison.setEnabled(true);
                    mnShowInputCSV.setEnabled(true);
                }
            }
        });
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
                decisionTreePredictInternalFrame.setLocation((frame.getWidth() - decisionTreePredictInternalFrame.getWidth())/2,
                        (frame.getHeight()- decisionTreePredictInternalFrame.getHeight())/2);
                decisionTreePredictInternalFrame.pack();
                decisionTreePredictInternalFrame.setClosable(true);
            }
        });
        mnDecisionTree.add(mntmDTPrediction);
        JMenuItem mntmDTForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmDTForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                DecisionTreeForecastVsActual decisionTreeForecastVsActualInternalFrame = new DecisionTreeForecastVsActual(csvPath, MainWindow.this);
                frame.add(decisionTreeForecastVsActualInternalFrame);
                decisionTreeForecastVsActualInternalFrame.setVisible(true);
                decisionTreeForecastVsActualInternalFrame.setLocation((frame.getWidth() - decisionTreeForecastVsActualInternalFrame.getWidth())/2,
                        (frame.getHeight()- decisionTreeForecastVsActualInternalFrame.getHeight())/2);
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
        JMenuItem mntmETForecastVsActual = new JMenuItem("Predict Vs Actual");
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
        JMenuItem mntmGBForecastVsActual = new JMenuItem("Predict Vs Actual");
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
        JMenuItem mntmKNNForecastVsActual = new JMenuItem("Predict Vs Actual");
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
        JMenuItem mntmLDForecastVsActual = new JMenuItem("Predict Vs Actual");
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
        JMenuItem mntmLiRForecastVsActual = new JMenuItem("Predict Vs Actual");
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
        JMenuItem mntmLSVMForecastVsActual = new JMenuItem("Predict Vs Actual");
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
        JMenuItem mntmLoRForecastVsActual = new JMenuItem("Predict Vs Actual");
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


        //Gaussian Naive Bayes

        JMenu mnGaussianNaiveBayes = new JMenu("Gaussian Naive Bayes");
        mnNaiveBayes.add(mnGaussianNaiveBayes);
        JMenuItem mntmGNBPrediction = new JMenuItem("Prediction");
        mntmGNBPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                GaussianNaiveBayesPredict gaussianNaiveBayesPredictInternalFrame = new GaussianNaiveBayesPredict(csvPath, MainWindow.this);
                frame.add(gaussianNaiveBayesPredictInternalFrame);
                gaussianNaiveBayesPredictInternalFrame.setVisible(true);
                gaussianNaiveBayesPredictInternalFrame.pack();
                gaussianNaiveBayesPredictInternalFrame.setClosable(true);
            }
        });
        mnGaussianNaiveBayes.add(mntmGNBPrediction);
        JMenuItem mntmGNBForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmGNBForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                GaussianNaiveBayesForecastVsActual gaussianNaiveBayesForecastVsActualInternalFrame = new GaussianNaiveBayesForecastVsActual(csvPath, MainWindow.this);
                frame.add(gaussianNaiveBayesForecastVsActualInternalFrame);
                gaussianNaiveBayesForecastVsActualInternalFrame.setVisible(true);
                gaussianNaiveBayesForecastVsActualInternalFrame.pack();
                gaussianNaiveBayesForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnGaussianNaiveBayes.add(mntmGNBForecastVsActual);


        //Bernoulli Naive Bayes

        JMenu mnBernoulliNaiveBayes = new JMenu("Bernoulli Naive Bayes");
        mnNaiveBayes.add(mnBernoulliNaiveBayes);
        JMenuItem mntmBNBPrediction = new JMenuItem("Prediction");
        mntmBNBPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                BernoulliNaiveBayesPredict bernoulliNaiveBayesPredictInternalFrame = new BernoulliNaiveBayesPredict(csvPath, MainWindow.this);
                frame.add(bernoulliNaiveBayesPredictInternalFrame);
                bernoulliNaiveBayesPredictInternalFrame.setVisible(true);
                bernoulliNaiveBayesPredictInternalFrame.pack();
                bernoulliNaiveBayesPredictInternalFrame.setClosable(true);
            }
        });
        mnBernoulliNaiveBayes.add(mntmBNBPrediction);
        JMenuItem mntmBNBForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmBNBForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                BernoulliNaiveBayesForecastVsActual bernoulliNaiveBayesForecastVsActualInternalFrame = new BernoulliNaiveBayesForecastVsActual(csvPath, MainWindow.this);
                frame.add(bernoulliNaiveBayesForecastVsActualInternalFrame);
                bernoulliNaiveBayesForecastVsActualInternalFrame.setVisible(true);
                bernoulliNaiveBayesForecastVsActualInternalFrame.pack();
                bernoulliNaiveBayesForecastVsActualInternalFrame.setClosable(true);
            }
        });
        mnBernoulliNaiveBayes.add(mntmBNBForecastVsActual);




        //*************************
        //**** Neural Networks ****
        //*************************

        JMenu mnNeuralNetworks = new JMenu("Neural Networks");
        mnTools.add(mnNeuralNetworks);
        JMenuItem mntmNNPrediction = new JMenuItem("Prediction");
        mntmNNPrediction.addActionListener(new ActionListener()
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
        mnNeuralNetworks.add(mntmNNPrediction);
        JMenuItem mntmNNForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmNNForecastVsActual.addActionListener(new ActionListener()
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
        mnNeuralNetworks.add(mntmNNForecastVsActual);


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
        JMenuItem mntmRFForecastVsActual = new JMenuItem("Predict Vs Actual");
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
        JMenuItem mntmRidgeForecastVsActual = new JMenuItem("Predict Vs Actual");
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
        JMenuItem mntmSGDForecastVsActual = new JMenuItem("Predict Vs Actual");
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
        JMenuItem mntmSVMForecastVsActual = new JMenuItem("Predict Vs Actual");
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
        mnTimeSeriesAnalysis.add(mntmTSAContinuousForecast);



        //*********************
        //*********************
        //**** SHOW VALUES ****
        //*********************
        //*********************


        //keyToPredict column, actionTime values
        //keyToPredict column, all values
        //actionKeys columns, ActionTime values
        //actionKeys columns, all values
        //actionKeys Columns, actionTime Values
        //Columns Actual And Predicted, actiontime Values
        //all columns, actionTime values -- For Predict and Predict Vs Actual
        //all columns, all values -- For Predict and Predict Vs Actual

        JMenu mnShowValues = new JMenu("Show values");

        mnShowInputCSV = new JMenuItem("csv"); // done
        JMenu mnSVPredict = new JMenu("Predict");
        JMenu mnSVPredictVsActual = new JMenu("Predict Vs Actual");
        JMenu mnSVTimeSeriesAnalysis = new JMenu("Time Series Analysis");

        //Predict
        mnSVPredict.add(new JMenuItem("all values"));
        mnSVPredict.add(new JMenuItem("all values for action time"));
        mnSVPredict.add(new JMenuItem("action keys values"));
        mnSVPredict.add(new JMenuItem("action keys values for action time"));
        mnSVPredict.add(new JMenuItem("key to predict values"));
        mnSVPredict.add(new JMenuItem("key to predict values for action time"));


        //Predict vs Actual
        mnSVPredictVsActual.add(new JMenuItem("all values"));
        mnSVPredictVsActual.add(new JMenuItem("all values for action time"));
        mnSVPredictVsActual.add(new JMenuItem("Actual and Predicted values for action time"));
        mnSVPredictVsActual.add(new JMenuItem("action keys columns for action time"));



        //Time Series Analysis
        JMenuItem mnItforecastOnce  = new JMenuItem("Forecast Once");
        JMenuItem mnItforecastVsActual = new JMenuItem("Forecast Vs Actual");
        JMenu mnItContinuousForecast = new JMenu("Continuous Forecast");

        mnSVTimeSeriesAnalysis.add(mnItforecastOnce);
        mnItforecastOnce.setEnabled(false);
        mnSVTimeSeriesAnalysis.add(mnItforecastVsActual);
        mnItforecastVsActual.setEnabled(false);
        mnSVTimeSeriesAnalysis.add(mnItContinuousForecast);

        mnItContinuousForecast.setEnabled(false);


        JMenuItem mnItContinuousForecastAllValues = new JMenuItem("all values");
        JMenuItem mnItContinuousForecastAbsoluteError = new JMenuItem("Absolute Errors");
        mnItContinuousForecastAbsoluteError.setEnabled(false);
        mnItContinuousForecastAllValues.setEnabled(false);
        mnItContinuousForecast.add(mnItContinuousForecastAllValues);
        mnItContinuousForecast.add(mnItContinuousForecastAbsoluteError);



        mnSVPredict.setEnabled(false);
        mnSVPredictVsActual.setEnabled(false);
        mnSVTimeSeriesAnalysis.setEnabled(false);
        mnShowInputCSV.setEnabled(false);


        mnShowValues.add(mnShowInputCSV);
        mnShowValues.add(mnSVPredict);
        mnShowValues.add(mnSVPredictVsActual);
        mnShowValues.add(mnSVTimeSeriesAnalysis);

        menuBar.add(mnShowValues);

        mnShowInputCSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> result =
                        CSVReader.getDataCSVForKeys(csvPath, CSVReader.getColumnKeys(csvPath));
                JInternalFrame masterFrame = new JInternalFrame();
                masterFrame.add(new ShowValues(result,masterFrame,MainWindow.this));
                String [] tmp = csvPath.split("/");
                String csvFile = tmp[tmp.length-1];
                MainWindow.this.add(masterFrame);
                masterFrame.setTitle(csvFile);
                masterFrame.setVisible(true);
                masterFrame.setClosable(true);
                masterFrame.pack();

            }
        });




        //*******************
        //*******************
        //**** Comparison ****
        //*******************
        //*******************

        mnComparison = new JMenu("Comparison");
        menuBar.add(mnComparison);
        JMenuItem mntmCompareTools = new JMenuItem("Compare Tools");
        mnComparison.add(mntmCompareTools);
        mntmCompareTools.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame masterFrame = new JInternalFrame();
                masterFrame.add(new CompareToolsPanel(csvPath,masterFrame,MainWindow.this));
                MainWindow.this.add(masterFrame);
                masterFrame.setVisible(true);
                masterFrame.setClosable(true);
                masterFrame.pack();
            }
        });
        mnComparison.setEnabled(false);



        //*******************
        //*******************
        //**** MENU HELP ****
        //*******************
        //*******************

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
