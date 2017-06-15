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
import swissknife.views.neuralnetwork.NeuralNetworkForecastVsActual;
import swissknife.views.neuralnetwork.NeuralNetworkPredict;
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
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by ramyeid on 6/14/17.
 */
//TODO add Panel that the user can choose 2 keys and plot them.
//TODO fix linear regression test it and do it // hint check Resources.java and add linearregression in variables.

public class MainWindowFrame extends JFrame{

    String csvPath;


    private JMenu mnTools;
    private JMenu mnComparison;
    private JMenu mnShowValues;
    private JMenuItem mnShowInputCSV;
    JDesktopPane dp;

    public MainWindowFrame()

    {
        this.setTitle("a");
        setTitle("Pattern Prediction and Forecasting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        dp = new JDesktopPane();
        setContentPane(dp);//add JDesktop pane to the content pane

        dp.setBackground(Color.gray);





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
                    mnShowValues.setEnabled(true);
                    mnShowInputCSV.setEnabled(true);
                }
            }
        });
        JMenuItem mntmClose = new JMenuItem("Close");
        mntmClose.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                MainWindowFrame.this.dispose();
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

                DecisionTreePredict iF = new DecisionTreePredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane
            }
        });
        mnDecisionTree.add(mntmDTPrediction);
        JMenuItem mntmDTForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmDTForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                DecisionTreeForecastVsActual iF = new DecisionTreeForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane
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

                ExtraTreePredict iF = new ExtraTreePredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane
            }
        });
        mnExtraTree.add(mntmETPrediction);




        JMenuItem mntmETForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmETForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ExtraTreeForecastVsActual iF = new ExtraTreeForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

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
                GradientBoostingPredict iF = new GradientBoostingPredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane
            }
        });
        mnGradientBoosting.add(mntmGBPrediction);
        JMenuItem mntmGBForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmGBForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                GradientBoostingForecastVsActual iF = new GradientBoostingForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

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
                KNearestNeighborsPredict iF = new KNearestNeighborsPredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

            }
        });
        mnKNearestNeighbors.add(mntmKNNPrediction);
        JMenuItem mntmKNNForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmKNNForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                KNearestNeighborsForecastVsActual iF = new KNearestNeighborsForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane
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
                LinearDiscriminantPredict iF = new LinearDiscriminantPredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

            }
        });
        mnLinearDiscriminant.add(mntmLDPrediction);
        JMenuItem mntmLDForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmLDForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                LinearDiscriminantForecastVsActual iF = new LinearDiscriminantForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane
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
//
//                LinearRegressionPredict iF = new LinearRegressionPredict(csvPath);
//                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
//                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);
//
//                iF.setClosable(true);
//                iF.setVisible(true);
//                dp.add(iF);//add internal frame to the desktop pane

                LinearRegressionPredict iF = new LinearRegressionPredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane
            }
        });
        mnLinearRegression.add(mntmLiRPrediction);
        JMenuItem mntmLiRForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmLiRForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


//                LinearRegressionForecastVsActual iF = new LinearRegressionForecastVsActual(csvPath);
//                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
//                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);
//
//                iF.setClosable(true);
//                iF.setVisible(true);
//                dp.add(iF);//add internal frame to the desktop pane
                LinearRegressionForecastVsActual iF = new LinearRegressionForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane
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


                LinearSvmPredict iF = new LinearSvmPredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

            }
        });
        mnLinearSvm.add(mntmLSVMPrediction);
        JMenuItem mntmLSVMForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmLSVMForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                LinearSvmForecastVsActual iF = new LinearSvmForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

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


                LogisticRegressionPredict iF = new LogisticRegressionPredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane


            }
        });
        mnLogisticRegression.add(mntmLoRPrediction);
        JMenuItem mntmLoRForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmLoRForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


                LogisticRegressionForecastVsActual iF = new LogisticRegressionForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane


            }
        });
        mnLogisticRegression.add(mntmLoRForecastVsActual);



        //*********************
        //**** Naive Bayes ****
        //*********************

        JMenu mnNaiveBayes = new JMenu("Naive Bayes");
        mnTools.add(mnNaiveBayes);


        //Bernoulli Naive Bayes

        JMenu mnBernoulliNaiveBayes = new JMenu("Bernoulli Naive Bayes");
        mnNaiveBayes.add(mnBernoulliNaiveBayes);
        JMenuItem mntmBNBPrediction = new JMenuItem("Prediction");
        mntmBNBPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                BernoulliNaiveBayesPredict iF = new BernoulliNaiveBayesPredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane


            }
        });
        mnBernoulliNaiveBayes.add(mntmBNBPrediction);
        JMenuItem mntmBNBForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmBNBForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


                BernoulliNaiveBayesForecastVsActual iF = new BernoulliNaiveBayesForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane


            }
        });
        mnBernoulliNaiveBayes.add(mntmBNBForecastVsActual);


        //Gaussian Naive Bayes

        JMenu mnGaussianNaiveBayes = new JMenu("Gaussian Naive Bayes");
        mnNaiveBayes.add(mnGaussianNaiveBayes);
        JMenuItem mntmGNBPrediction = new JMenuItem("Prediction");
        mntmGNBPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                GaussianNaiveBayesPredict iF = new GaussianNaiveBayesPredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane


            }
        });
        mnGaussianNaiveBayes.add(mntmGNBPrediction);
        JMenuItem mntmGNBForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmGNBForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


                GaussianNaiveBayesForecastVsActual iF = new GaussianNaiveBayesForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

            }
        });
        mnGaussianNaiveBayes.add(mntmGNBForecastVsActual);

        //********************************
        //**** Neural Network         ****
        //********************************

        JMenu mnNeuralNetwork = new JMenu("Neural Network");
        mnTools.add(mnNeuralNetwork);
        JMenuItem mntmNNPrediction = new JMenuItem("Prediction");
        mntmNNPrediction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                NeuralNetworkPredict iF = new NeuralNetworkPredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane


            }
        });
        mnNeuralNetwork.add(mntmNNPrediction);
        JMenuItem mntmNNForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmNNForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


                NeuralNetworkForecastVsActual iF = new NeuralNetworkForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

            }
        });
        mnNeuralNetwork.add(mntmNNForecastVsActual);




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


                RandomForestPredict iF = new RandomForestPredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane


            }
        });
        mnRandomForest.add(mntmRFPrediction);
        JMenuItem mntmRFForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmRFForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


                RandomForestForecastVsActual iF = new RandomForestForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

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


                RidgePredict iF = new RidgePredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane


            }
        });
        mnRidge.add(mntmRidgePredicition);
        JMenuItem mntmRidgeForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmRidgeForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


                RidgeForecastVsActual iF = new RidgeForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

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



                StochasticGradientDescentPredict iF = new StochasticGradientDescentPredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

            }
        });
        mnStochasticGradientDescent.add(mntmSGDPrediction);
        JMenuItem mntmSGDForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmSGDForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


                StochasticGradientDescentForecastVsActual iF = new StochasticGradientDescentForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane


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

                SupportVectorMachinePredict iF = new SupportVectorMachinePredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane


            }
        });
        mnSupportVectotMachine.add(mntmSVMPrediction);
        JMenuItem mntmSVMForecastVsActual = new JMenuItem("Predict Vs Actual");
        mntmSVMForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


                SupportVectorMachineForecastVsActual iF = new SupportVectorMachineForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

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


                TimeSeriesPredict iF = new TimeSeriesPredict(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane


            }
        });
        mnTimeSeriesAnalysis.add(mntmTSAPrediction);
        JMenuItem mntmTSAForecastOnce = new JMenuItem("Forecast Once");
        mntmTSAForecastOnce.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


                TimeSeriesForecastOnce iF = new TimeSeriesForecastOnce(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane


            }
        });
        mnTimeSeriesAnalysis.add(mntmTSAForecastOnce);
        JMenuItem mntmTSAForecastVsActual = new JMenuItem("Forecast Vs Actual");
        mntmTSAForecastVsActual.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


                TimeSeriesForecastVsActual iF = new TimeSeriesForecastVsActual(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

            }
        });
        mnTimeSeriesAnalysis.add(mntmTSAForecastVsActual);
        JMenuItem mntmTSAContinuousForecast = new JMenuItem("Continuous Forecast");
        mntmTSAContinuousForecast.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


                TimeSeriesContinuousForecast iF = new TimeSeriesContinuousForecast(csvPath, MainWindowFrame.this);
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                iF.setVisible(true);
                dp.add(iF);//add internal frame to the desktop pane

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

        mnShowValues = new JMenu("Show Values");

        mnShowInputCSV = new JMenuItem("Loaded CSV"); // done
        JMenu mnSVPredict = new JMenu("Predict");
        JMenu mnSVPredictVsActual = new JMenu("Predict Vs Actual");
        JMenu mnSVTimeSeriesAnalysis = new JMenu("Time Series Analysis");

        //Predict
        mnSVPredict.add(new JMenuItem("All values"));
        mnSVPredict.add(new JMenuItem("All values for action time"));
        mnSVPredict.add(new JMenuItem("Action keys values"));
        mnSVPredict.add(new JMenuItem("Action keys values for action time"));
        mnSVPredict.add(new JMenuItem("Key to predict values"));
        mnSVPredict.add(new JMenuItem("Key to predict values for action time"));


        //Predict vs Actual
        mnSVPredictVsActual.add(new JMenuItem("All values"));
        mnSVPredictVsActual.add(new JMenuItem("All values for action time"));
        mnSVPredictVsActual.add(new JMenuItem("Actual and Predicted values for action time"));
        mnSVPredictVsActual.add(new JMenuItem("Action keys columns for action time"));



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


        JMenuItem mnItContinuousForecastAllValues = new JMenuItem("All values");
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
                JInternalFrame iF = new JInternalFrame();
                iF.add(new ShowValues(result, iF, MainWindowFrame.this));

                String[] tmp = csvPath.split("/");
                String csvFile = tmp[tmp.length - 1];
                iF.setTitle(csvFile);
                iF.setClosable(true);
                iF.setVisible(true);
                iF.pack();

                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                dp.add(iF);//add internal frame to the desktop pane


            }
        });

        mnShowValues.setEnabled(false);




        //********************
        //********************
        //**** Comparison ****
        //********************
        //********************

        mnComparison = new JMenu("Comparison");
        menuBar.add(mnComparison);
        JMenuItem mntmCompareTools = new JMenuItem("Compare Tools");
        mnComparison.add(mntmCompareTools);
        mntmCompareTools.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<ArrayList<String>> result =
                        CSVReader.getDataCSVForKeys(csvPath, CSVReader.getColumnKeys(csvPath));
                JInternalFrame iF = new JInternalFrame();
                iF.add(new CompareToolsPanel(csvPath,iF,MainWindowFrame.this));

                iF.setTitle("Comparison Tool");
                iF.setClosable(true);
                iF.setVisible(true);
                iF.pack();
                iF.setLocation((MainWindowFrame.this.getWidth() - iF.getWidth())/2,
                        (MainWindowFrame.this.getHeight()- iF.getHeight())/2);

                iF.setClosable(true);
                dp.add(iF);//add internal frame to the desktop pane



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






        this.setVisible(true);
    }

    public static void main(String []args){
        new MainWindowFrame();
    }


    public JDesktopPane getDesktopPanel(){
        return dp;
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
