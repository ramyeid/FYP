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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MainWindow extends JFrame
{

	private JPanel contentPane;
	private static MainWindow frame;
	
	private JInternalFrame csvInternalFrame;
	
	private JTextField pathToCsv;
	private ArrayList<ArrayList<String>> data;
	
	public static ArrayList<ArrayList<String>> readCSV (String path)
    {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int lineNum = 0;
        ArrayList<ArrayList<String>> mat = new ArrayList<ArrayList<String>>();
        
        try
        {
            br = new BufferedReader(new FileReader(path));
            
            while ((line = br.readLine()) != null)
            {
                if (lineNum==0)
                {}
                
                else
                {
                // use comma as separator
                String[] temp = line.split(cvsSplitBy);
                 
                ArrayList<String> arr = new ArrayList<String>();
                 
                for(int i=0; i<temp.length; ++i)
                {
                arr.add(temp[i]);
                }
                 
            mat.add(arr);
                }
                
                lineNum++;
            }

        }
        
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        finally
        {
            if (br != null)
            {
                try 
                {
                    br.close();
                }
                
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        return mat;
    }
	

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
		mntmAbout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnHelp.add(mntmAbout);
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
					JOptionPane.showMessageDialog(null, "Please choose a file with a .csv extension");
				}
				
				else
				{
					String csvPath = pathToCsv.getText();
					data = readCSV(csvPath);
					mnAlgorithms.setEnabled(true);
					csvInternalFrame.dispose();
				}
				
			}
		});
		btnDoneCsv.setBounds(292, 223, 105, 30);
		csvInternalFrame.getContentPane().add(btnDoneCsv);
	}
}
