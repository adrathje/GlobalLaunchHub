import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

/***************************************************************************
* Name        : GlobalLaunchApplication
* Author      : Austin Rathje
* Created     : 4/20/2020 
* Course      : CIS 152 Data Structures
* Version     : 1.0
* OS          : Windows 10
* Copyright   : This is my own original work based on
*               specifications issued by our instructor
* Description : ......
*  
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or 
* unmodified. I have not given other fellow student(s) access to
* my program.         
***************************************************************************/

public class GlobalLaunchApplication {

	private JFrame frame;
	private JTextField addDateText;
	private JTextField editByIdText;
	private JTextField addRocketText;
	private JTextField addCostText;
	private JTextField addBeneficiaryText;
	private JTextField addProviderText;
	private JTextField editDateText;
	private JTextField editRocketText;
	private JTextField editCostText;
	private JTextField editBeneficiaryText;
	private JTextField editProviderText;
	private JTextArea textArea;
	private JPanel editPanel;
	private JLabel editSuccessLabel;
	private String infoHeader = "ID | Launch Date | Rocket | Launch Provider | Beneficiary | Cost (in thousands)\n";
	private LinkedList<Contract> contracts = new LinkedList<Contract>();
	Queue<Contract> upcomingLaunches = new LinkedList<>();
	private int idCounter = 5;
	private int editContractId;
	
	// set date time format
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

	/********************************************
	 * Testing Dummy Contracts
	 ********************************************/
	Contract contract1 = new Contract(1, LocalDate.parse("05-01-2020", formatter), "Falcon 9", "SpaceX", "NASA", 9000.0);
	Contract contract2 = new Contract(2, LocalDate.parse("09-02-2020", formatter), "Delta Heavy", "ULA", "NASA/Politicians", 15000.0);
	Contract contract3 = new Contract(3, "New Glenn", "Blue Origin", "NASA", 10000);
	Contract contract4 = new Contract(4, LocalDate.parse("09-01-2020", formatter), "Hyperbola-2", "Ispace", "Chinese Government", 13000.0);
	Contract contract5 = new Contract(5, LocalDate.parse("07-08-2020", formatter), "Hyperbola-2", "Ispace", "Chinese Government", 13000.0);

	boolean add1 = contracts.add(contract1);
	boolean add2 = contracts.add(contract2);
	boolean add3 = contracts.add(contract3);
	boolean add4 = contracts.add(contract4);
	boolean add5 = contracts.add(contract5);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlobalLaunchApplication window = new GlobalLaunchApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GlobalLaunchApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1221, 867);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("Global Launch Contracts Hub");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		titleLabel.setBounds(240, 30, 382, 32);
		frame.getContentPane().add(titleLabel);

		JLabel lblNewLabel = new JLabel("View contracts sorted by:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(49, 87, 177, 17);
		frame.getContentPane().add(lblNewLabel);

		/**
		 * display sorted contracts by cost
		 */
		JButton costButton = new JButton("Cost");
		costButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textArea.append(infoHeader);
				
				
				//LocalDate today = LocalDate.parse("2019-03-29", formatter);
				//LocalDate date = LocalDate.now();
				//System.out.println(date.format(formatter));
				
				

				// sort contracts by cost
				Collections.sort(contracts, new Comparator<Contract>() {

					@Override
					public int compare(Contract c1, Contract c2) {
						return c1.getCost() < c2.getCost() ? -1 : c1.getCost() == c2.getCost() ? 0 : 1;
					}
				});

				// display contracts
				displayContracts();
			}
		});
		costButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		costButton.setBounds(235, 85, 65, 23);
		frame.getContentPane().add(costButton);

		/**
		 * display sorted contracts by rocket
		 */
		JButton rocketButton = new JButton("Rocket");
		rocketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				textArea.append(infoHeader);

				// sort contracts by rocket
				Collections.sort(contracts, new Comparator<Contract>() {

					@Override
					public int compare(Contract c1, Contract c2) {
						return c1.getRocket().compareTo(c2.getRocket()) < 0 ? -1 : c1.getRocket().equals(c2.getRocket()) ? 0 : 1;
					}
				});
				
				// display contracts
				displayContracts();
			}
		});
		rocketButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rocketButton.setBounds(306, 85, 75, 23);
		frame.getContentPane().add(rocketButton);

		/**
		 * display sorted contracts by launch date
		 */
		JButton dateButton = new JButton("Launch Date");
		dateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textArea.append(infoHeader);
				
				// save todays date and format it
				LocalDate today = LocalDate.now();
				today.format(formatter);
				LinkedList<Contract> contractsByDate = new LinkedList<Contract>();
				
				for(Contract c : contracts) {
					if(c.getLaunchDate() != null && c.getLaunchDate().isAfter(today)) {
						contractsByDate.add(c);
					}
				}
				
				// sort contracts by launch provider
				Collections.sort(contractsByDate, new Comparator<Contract>() {

					@Override
					public int compare(Contract c1, Contract c2) {
						return c1.getLaunchDate().isBefore(c2.getLaunchDate()) ? -1 : c1.getLaunchDate().isEqual(c2.getLaunchDate()) ? 0 : 1;
					}
				});
				
				// display contracts with dates
				for (Contract c : contractsByDate) {
						textArea.append(c.getContractId() + " | " + c.getLaunchDate().format(formatter) + " | " + c.getRocket() + c.getLaunchProvider() + " | " + c.getBeneficiary() + " | " + c.getCost() + "\n");
				}
				
				// display contracts with no dates
				for (Contract c : contracts) {
					if(c.getLaunchDate() == null) {
						textArea.append(c.getContractId() + " | No Date | " + c.getRocket() + c.getLaunchProvider() + " | " + c.getBeneficiary() + " | " + c.getCost() + "\n");
					} 
				}
			}
		});
		dateButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateButton.setBounds(385, 85, 111, 23);
		frame.getContentPane().add(dateButton);

		JLabel newContractLabel = new JLabel("Add a new contract");
		newContractLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		newContractLabel.setBounds(74, 142, 142, 19);
		frame.getContentPane().add(newContractLabel);

		JLabel lblNewLabel_3 = new JLabel("Edit contract (search by ID)");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(349, 143, 192, 17);
		frame.getContentPane().add(lblNewLabel_3);

		editByIdText = new JTextField();
		editByIdText.setHorizontalAlignment(SwingConstants.CENTER);
		editByIdText.setBounds(551, 143, 114, 20);
		frame.getContentPane().add(editByIdText);
		editByIdText.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 191, 255));
		separator.setBounds(51, 118, 798, 2);
		frame.getContentPane().add(separator);

		/**********************************************
		 * Edit Contract Section
		 ***********************************************/

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// local variable
				int contractId;
				boolean found = false;

				// check if input is valid
				if (isInteger(editByIdText.getText().trim()) && !editByIdText.getText().trim().isEmpty()) {
					contractId = Integer.parseInt(editByIdText.getText().trim());
					editByIdText.setBackground(Color.white);

					// find matching contract ID and populate the edit contract form
					for (Contract c : contracts) {
						if (c.getContractId() == contractId) {
							if(c.getLaunchDate() != null) {
								editDateText.setText(c.getLaunchDate().format(formatter));
							} else {
								editDateText.setText("");
							}
							editProviderText.setText(c.getLaunchProvider());
							editRocketText.setText(c.getRocket());
							editCostText.setText(Double.toString(c.getCost()));
							editBeneficiaryText.setText(c.getBeneficiary());
							editPanel.setVisible(true);
							editContractId = c.getContractId();
							found = true;
							break;
						}
					}
					if (!found) {
						editByIdText.setBackground(Color.pink);
						editByIdText.setText("Did not find");
						editPanel.setVisible(false);
					}
				} else {
					editByIdText.setBackground(Color.pink);
					editByIdText.setText("Enter valid number");
					editPanel.setVisible(false);
				}
			}
		});
		editButton.setBackground(new Color(0, 191, 255));
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editButton.setBounds(670, 141, 57, 25);
		frame.getContentPane().add(editButton);

		JScrollPane dataDisplay = new JScrollPane();
		dataDisplay.setBounds(49, 409, 556, 312);
		frame.getContentPane().add(dataDisplay);
		
				textArea = new JTextArea();
				dataDisplay.setViewportView(textArea);
				textArea.setFont(new Font("Times New Roman", Font.BOLD, 14));

		JLabel lblNewLabel_4 = new JLabel("Contract Data");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(261, 376, 126, 22);
		frame.getContentPane().add(lblNewLabel_4);

		/**
		 * display sorted contracts by launch provider
		 */
		JButton providerButton = new JButton("Launch Provider");
		providerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textArea.append(infoHeader);

				// sort contracts by launch provider
				Collections.sort(contracts, new Comparator<Contract>() {

					@Override
					public int compare(Contract c1, Contract c2) {
						return c1.getLaunchProvider().compareTo(c2.getLaunchProvider()) < 0 ? -1 : c1.getLaunchProvider().equals(c2.getLaunchProvider()) ? 0 : 1;
					}
				});
				
				// display contracts
				displayContracts();
			}
		});
		providerButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		providerButton.setBounds(500, 85, 126, 23);
		frame.getContentPane().add(providerButton);

		/**
		 * display sorted contracts by beneficiary
		 */
		JButton beneficiaryButton = new JButton("Beneficiary");
		beneficiaryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textArea.append(infoHeader);

				// sort contracts by launch provider
				Collections.sort(contracts, new Comparator<Contract>() {

					@Override
					public int compare(Contract c1, Contract c2) {
						return c1.getBeneficiary().compareTo(c2.getBeneficiary()) < 0 ? -1 : c1.getBeneficiary().equals(c2.getBeneficiary()) ? 0 : 1;
					}
				});
				
				// display contracts
				displayContracts();
			}
		});
		beneficiaryButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		beneficiaryButton.setBounds(630, 85, 97, 23);
		frame.getContentPane().add(beneficiaryButton);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(0, 191, 255));
		separator_2.setBounds(49, 11, 769, 2);
		frame.getContentPane().add(separator_2);

		editPanel = new JPanel();
		editPanel.setBounds(383, 190, 222, 164);
		frame.getContentPane().add(editPanel);
		editPanel.setLayout(null);

		JLabel editDateLabel = new JLabel("Launch Date:");
		editDateLabel.setBounds(10, 11, 73, 14);
		editPanel.add(editDateLabel);
		editDateLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		editDateText = new JTextField();
		editDateText.setBounds(86, 8, 119, 20);
		editPanel.add(editDateText);
		editDateText.setHorizontalAlignment(SwingConstants.CENTER);
		editDateText.setColumns(10);

		JLabel editRocketLabel = new JLabel("Rocket:");
		editRocketLabel.setBounds(10, 36, 47, 14);
		editPanel.add(editRocketLabel);
		editRocketLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		editRocketText = new JTextField();
		editRocketText.setBounds(58, 33, 147, 20);
		editPanel.add(editRocketText);
		editRocketText.setHorizontalAlignment(SwingConstants.CENTER);
		editRocketText.setColumns(10);

		JLabel editCostLabel = new JLabel("Cost (in thousands):");
		editCostLabel.setBounds(10, 61, 113, 14);
		editPanel.add(editCostLabel);
		editCostLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		editCostText = new JTextField();
		editCostText.setBounds(127, 58, 78, 20);
		editPanel.add(editCostText);
		editCostText.setHorizontalAlignment(SwingConstants.CENTER);
		editCostText.setColumns(10);

		JLabel editBeneficiaryLabel = new JLabel("Beneficiary:");
		editBeneficiaryLabel.setBounds(10, 86, 66, 14);
		editPanel.add(editBeneficiaryLabel);
		editBeneficiaryLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		editBeneficiaryText = new JTextField();
		editBeneficiaryText.setBounds(81, 83, 124, 20);
		editPanel.add(editBeneficiaryText);
		editBeneficiaryText.setHorizontalAlignment(SwingConstants.CENTER);
		editBeneficiaryText.setColumns(10);

		JLabel editProviderLabel = new JLabel("Launch Provider:");
		editProviderLabel.setBounds(10, 111, 94, 14);
		editPanel.add(editProviderLabel);
		editProviderLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		editProviderText = new JTextField();
		editProviderText.setBounds(107, 108, 98, 20);
		editPanel.add(editProviderText);
		editProviderText.setHorizontalAlignment(SwingConstants.CENTER);
		editProviderText.setColumns(10);

		/****************************************
		 * Submit Edit Button
		 ***************************************/

		JButton editSumbitbutton = new JButton("Submit Edit");
		editSumbitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// local variables
				boolean allValid = true;
				String rocket = editRocketText.getText().trim();
				String launchProvider = editProviderText.getText().trim();
				String beneficiary = editBeneficiaryText.getText().trim();
				double cost = 0;

				// check if editRocketText is valid
				if (rocket.isEmpty() || editRocketText.getText().trim().equals("missing")) {
					editRocketText.setBackground(Color.pink);
					editRocketText.setText("missing");
					allValid = false;
				} else {
					editRocketText.setBackground(Color.white);
				}

				// check if editProviderText is valid
				if (launchProvider.isEmpty() || editProviderText.getText().trim().equals("missing")) {
					editProviderText.setBackground(Color.pink);
					editProviderText.setText("missing");
					allValid = false;
				} else {
					editProviderText.setBackground(Color.white);
				}

				// check if editBeneficiaryText is valid
				if (beneficiary.isEmpty() || editBeneficiaryText.getText().trim().equals("missing")) {
					editBeneficiaryText.setBackground(Color.pink);
					editBeneficiaryText.setText("missing");
					allValid = false;
				} else {
					editBeneficiaryText.setBackground(Color.white);
				}

				// check if cost input is valid
				if (isDouble(editCostText.getText().trim()) && !editCostText.getText().trim().isEmpty()) {
					cost = Double.parseDouble(editCostText.getText().trim());
					editCostText.setBackground(Color.white);
				} else {
					editCostText.setBackground(Color.pink);
					editCostText.setText("missing");
					allValid = false;
				}
				
				// check if LocalDate input is valid
				if (isDate(editDateText.getText().trim()) || editDateText.getText().trim().equals("")) {
					editDateText.setBackground(Color.white);
				} else {
					editDateText.setBackground(Color.pink);
					editDateText.setText("incorrect format");
					allValid = false;
				}

				// if all user inputs check out, add contract to list
				if (allValid) {
					for (Contract c : contracts) {
						if (c.getContractId() == editContractId) {
							if(!editDateText.getText().trim().equals("")) {
								c.setLaunchDate(LocalDate.parse(editDateText.getText().trim(), formatter));
							}
							c.setLaunchProvider(launchProvider);
							c.setRocket(rocket);
							c.setCost(cost);
							c.setBeneficiary(beneficiary);
							editByIdText.setText("");
							editPanel.setVisible(false);
							editSuccessLabel.setVisible(true);
						}
					}
					
					// update textArea
					textArea.setText("");
					textArea.append(infoHeader);
					displayContracts();
				}
			}
		});
		editSumbitbutton.setBounds(10, 136, 195, 23);
		editPanel.add(editSumbitbutton);
		editSumbitbutton.setForeground(Color.BLACK);
		editSumbitbutton.setFont(new Font("Tahoma", Font.BOLD, 14));
		editSumbitbutton.setBackground(new Color(0, 191, 255));

		JPanel AddPanel = new JPanel();
		AddPanel.setBounds(45, 184, 214, 170);
		frame.getContentPane().add(AddPanel);
		AddPanel.setLayout(null);

		JTextArea txtrLaunchDate = new JTextArea();
		txtrLaunchDate.setBounds(5, 5, 86, 32);
		AddPanel.add(txtrLaunchDate);
		txtrLaunchDate.setLineWrap(true);
		txtrLaunchDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtrLaunchDate.setText("Launch Date: \r\n   (optional) ");
		txtrLaunchDate.setBackground(UIManager.getColor("TextField.disabledBackground"));

		addDateText = new JTextField();
		addDateText.setBounds(91, 11, 113, 20);
		AddPanel.add(addDateText);
		addDateText.setHorizontalAlignment(SwingConstants.CENTER);
		addDateText.setColumns(10);
		addDateText.setText("mm-dd-yyyy");

		JLabel lblRocket = new JLabel("Rocket:");
		lblRocket.setBounds(5, 40, 47, 14);
		AddPanel.add(lblRocket);
		lblRocket.setFont(new Font("Tahoma", Font.BOLD, 11));

		addRocketText = new JTextField();
		addRocketText.setBounds(54, 37, 150, 20);
		AddPanel.add(addRocketText);
		addRocketText.setHorizontalAlignment(SwingConstants.CENTER);
		addRocketText.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Cost (in thousands):");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(5, 65, 113, 14);
		AddPanel.add(lblNewLabel_1);

		addCostText = new JTextField();
		addCostText.setBounds(122, 62, 82, 20);
		AddPanel.add(addCostText);
		addCostText.setHorizontalAlignment(SwingConstants.CENTER);
		addCostText.setColumns(10);

		JLabel lblBeneficiary = new JLabel("Beneficiary:");
		lblBeneficiary.setBounds(5, 90, 66, 14);
		AddPanel.add(lblBeneficiary);
		lblBeneficiary.setFont(new Font("Tahoma", Font.BOLD, 11));

		addBeneficiaryText = new JTextField();
		addBeneficiaryText.setBounds(75, 87, 129, 20);
		AddPanel.add(addBeneficiaryText);
		addBeneficiaryText.setHorizontalAlignment(SwingConstants.CENTER);
		addBeneficiaryText.setColumns(10);

		JLabel lblLaunchProvider = new JLabel("Launch Provider :");
		lblLaunchProvider.setBounds(5, 115, 97, 14);
		AddPanel.add(lblLaunchProvider);
		lblLaunchProvider.setFont(new Font("Tahoma", Font.BOLD, 11));

		addProviderText = new JTextField();
		addProviderText.setBounds(107, 112, 97, 20);
		AddPanel.add(addProviderText);
		addProviderText.setHorizontalAlignment(SwingConstants.CENTER);
		addProviderText.setColumns(10);

		/**************************************
		 *  Add Contract Section
		 *************************************/
		
		JButton addSubmitButton = new JButton("Submit");
		addSubmitButton.setBounds(5, 140, 199, 23);
		AddPanel.add(addSubmitButton);
		addSubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// local variables
				boolean allValid = true;
				String rocket = addRocketText.getText().trim();
				String launchProvider = addProviderText.getText().trim();
				String beneficiary = addBeneficiaryText.getText().trim();
				double cost = 0;

				// check if addRocketText is valid
				if (rocket.isEmpty() || addRocketText.getText().trim().equals("missing")) {
					addRocketText.setBackground(Color.pink);
					addRocketText.setText("missing");
					allValid = false;
				} else {
					addRocketText.setBackground(Color.white);
				}

				// check if addProviderText is valid
				if (launchProvider.isEmpty() || addProviderText.getText().trim().equals("missing")) {
					addProviderText.setBackground(Color.pink);
					addProviderText.setText("missing");
					allValid = false;
				} else {
					addProviderText.setBackground(Color.white);
				}

				// check if addProviderText is valid
				if (beneficiary.isEmpty() || addBeneficiaryText.getText().trim().equals("missing")) {
					addBeneficiaryText.setBackground(Color.pink);
					addBeneficiaryText.setText("missing");
					allValid = false;
				} else {
					addBeneficiaryText.setBackground(Color.white);
				}

				// check if cost input is valid
				if (isDouble(addCostText.getText().trim()) && !addCostText.getText().trim().isEmpty()) {
					cost = Double.parseDouble(addCostText.getText().trim());
					addCostText.setBackground(Color.white);
				} else {
					addCostText.setBackground(Color.pink);
					addCostText.setText("missing");
					allValid = false;
				}

				// check if LocalDate input is valid
				if(addDateText.getText() != "" && addDateText.getText() != "mm-dd-yyyy") {
					if (isDate(addDateText.getText().trim())) {
						addDateText.setBackground(Color.white);
					} else {
						addDateText.setBackground(Color.pink);
						addDateText.setText("incorrect format");
						allValid = false;
					}
				}
				
				// if all user inputs check out, add contract to list
				if (allValid) {
					Contract newContract;
					idCounter++;
					
					// check if launch date is provided
					if (isDate(addDateText.getText().trim())) {
						// format and assign the launch date
						LocalDate launchDate = LocalDate.parse(addDateText.getText().trim(), formatter);
						
						// initialize new contract
						newContract = new Contract(launchDate, rocket, launchProvider, beneficiary, cost);
						
						// save todays date and format it
						LocalDate today = LocalDate.now();
						today.format(formatter);
						
						// check if contract is slated for an upcoming launch, if so add it to the upcomingLaunches Queue
						if(newContract.getLaunchDate() != null && newContract.getLaunchDate().isAfter(today)) {
							newContract.setContractId(idCounter);
							upcomingLaunches.add(newContract);
						}
					} else {
						newContract = new Contract(rocket, launchProvider, beneficiary, cost);
					}

					// add new contract to LinkedList
					newContract.setContractId(idCounter);
					contracts.add(newContract);

					// reset input area
					addBeneficiaryText.setText("");
					addCostText.setText("");
					addProviderText.setText("");
					addRocketText.setText("");
					addDateText.setText("mm-dd-yyyy");
					
					// update textArea
					textArea.setText("");
					textArea.append(infoHeader);
					displayContracts();
				}
			}
		});
		addSubmitButton.setForeground(SystemColor.desktop);
		addSubmitButton.setBackground(new Color(0, 191, 255));
		addSubmitButton.setFont(new Font("Tahoma", Font.BOLD, 14));

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBackground(new Color(0, 191, 255));
		separator_1.setBounds(318, 131, 11, 223);
		frame.getContentPane().add(separator_1);

		editSuccessLabel = new JLabel("Edit Successful");
		editSuccessLabel.setForeground(new Color(50, 205, 50));
		editSuccessLabel.setBackground(new Color(50, 205, 50));
		editSuccessLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		editSuccessLabel.setBounds(444, 171, 96, 16);
		editSuccessLabel.setVisible(false);
		frame.getContentPane().add(editSuccessLabel);

		JLabel manifestTitleLabel = new JLabel("Launch Manifest");
		manifestTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		manifestTitleLabel.setBounds(738, 297, 166, 25);
		frame.getContentPane().add(manifestTitleLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(670, 348, 323, 147);
		frame.getContentPane().add(scrollPane);
		
				JLabel upcomingManifestLabel = new JLabel("Upcoming Launches");
				upcomingManifestLabel.setHorizontalAlignment(SwingConstants.CENTER);
				scrollPane.setColumnHeaderView(upcomingManifestLabel);
				upcomingManifestLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
						JTextArea upcomingManifestTextArea = new JTextArea();
						scrollPane.setViewportView(upcomingManifestTextArea);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(676, 557, 321, 164);
		frame.getContentPane().add(scrollPane_1);
		
				JTextArea pastManifestTextArea = new JTextArea();
				scrollPane_1.setViewportView(pastManifestTextArea);
				
						JLabel pastManifestLabel = new JLabel("Past Launches");
						pastManifestLabel.setHorizontalAlignment(SwingConstants.CENTER);
						scrollPane_1.setColumnHeaderView(pastManifestLabel);
						pastManifestLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JButton launchButton = new JButton("Launch a Rocket!");
		launchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// pop a contract from the Queue and add it to the past launches area
				Contract poppedLaunch = upcomingLaunches.remove();
				pastManifestTextArea.append(poppedLaunch.getLaunchDate().format(formatter).toString() + " | " + poppedLaunch.getLaunchProvider() + " | "  + poppedLaunch.getRocket() + "\n");
				
				// reset text area then re-populate it
				upcomingManifestTextArea.setText("");
				for(Contract c : upcomingLaunches) {
					upcomingManifestTextArea.append(c.getLaunchDate().format(formatter).toString() + " | "  + c.getLaunchProvider() + " | "  + c.getRocket() + "\n");
				}
			}
		});
		launchButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		launchButton.setBounds(765, 507, 151, 25);
		frame.getContentPane().add(launchButton);
		
		JButton refreshButton = new JButton("");
		refreshButton.setIcon(new ImageIcon(GlobalLaunchApplication.class.getResource("/resources/refresh2 - Copy.png")));
		refreshButton.setBounds(993, 348, 46, 43);
		frame.getContentPane().add(refreshButton);
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// reset text area then re-populate it
				upcomingManifestTextArea.setText("");
				
				// save todays date and format it
				LocalDate today = LocalDate.now();
				today.format(formatter);
				LinkedList<Contract> contractsByDate = new LinkedList<Contract>();
				
				for(Contract c : contracts) {
					if(c.getLaunchDate() != null && c.getLaunchDate().isAfter(today)) {
						contractsByDate.add(c);
					}
				}
				
				// sort contracts by launch provider
				Collections.sort(contractsByDate, new Comparator<Contract>() {

					@Override
					public int compare(Contract c1, Contract c2) {
						return c1.getLaunchDate().isBefore(c2.getLaunchDate()) ? -1 : c1.getLaunchDate().isEqual(c2.getLaunchDate()) ? 0 : 1;
					}
				});
				
				
				for(Contract c : upcomingLaunches) {
					upcomingManifestTextArea.append(c.getLaunchDate().format(formatter).toString() + " | " + c.getLaunchProvider() + " | "  + c.getRocket() + "\n");
				}
			}
		});
		refreshButton.setForeground(Color.BLACK);
		refreshButton.setBackground(Color.LIGHT_GRAY);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 191, 255));
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(640, 307, 20, 413);
		frame.getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(0, 191, 255));
		separator_4.setBounds(641, 276, 352, 2);
		frame.getContentPane().add(separator_4);

		editPanel.setVisible(false);

	}

	/**
	 * displays all contracts in Contract Data area
	 */
	public void displayContracts() {
		for (Contract c : contracts) {
			if(c.getLaunchDate() != null) {
				textArea.append(c.getContractId() + " | " + c.getLaunchDate().format(formatter) + " | " + c.getRocket() + " | " + c.getLaunchProvider() + " | " + c.getBeneficiary() + " | " + c.getCost() + "\n");
			} else {
				textArea.append(c.getContractId() + " | No Date | " + c.getRocket() + " | " + c.getLaunchProvider() + " | " + c.getBeneficiary() + " | " + c.getCost() + "\n");
			}
		}
	}
	
	/**
	 * checks if string is in valid date format
	 * 
	 * @param possibleDate
	 * @return
	 */
	public boolean isDate(String possibleDate) {
		try {
			LocalDate.parse(possibleDate, formatter);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * checks if string is a double
	 * 
	 * @param possibleDouble
	 * @return
	 */
	public boolean isDouble(String possibleDouble) {
		try {
			Double.parseDouble(possibleDouble);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * checks if string is an integer
	 * 
	 * @param possibleInteger
	 * @return
	 */
	public boolean isInteger(String possibleInteger) {
		try {
			Integer.parseInt(possibleInteger);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
