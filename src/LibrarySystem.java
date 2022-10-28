import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author maho ando
 * @date Oct 23, 2022
 */
public class LibrarySystem<Type1> {
	
	private final String accountName;		// accountName
	
	private LinkedHashMap<String, Integer> bookHashMap = new LinkedHashMap<>();	// bookHashMap
	
	public LibrarySystem(String name) {
		this.accountName = name;
	}
	
	/**
	 * getter 
	 * @return bookHashMap
	*/
	public HashMap<String, Integer> getBookHashMap() {
		bookHashMap.put("novel", 0);
		bookHashMap.put("fiction", 0);
		bookHashMap.put("non-fiction", 0);
		bookHashMap.put("comic", 0);
		bookHashMap.put("magazine", 0);
		bookHashMap.put("cookbook", 0);
		return bookHashMap;
	}

	/**
	 *  showing library machine menu
	 */
	public void showMenu() {
		char option = '\0';
		getBookHashMap();
		
		System.out.println( "==================================" + 
							CoderConstance.LINE_SEPARATOR +
							"Hi, " + accountName  + 
							CoderConstance.LINE_SEPARATOR +
							"Weclome to the Library" );
		do {
			//+++++++++++++++++++++ 
			// top menu
			//+++++++++++++++++++++ 
			System.out.println( "==================================" + 
								CoderConstance.LINE_SEPARATOR +
								"1. Library Information" + 
								CoderConstance.LINE_SEPARATOR + 
								"2. Borrowing" + 
								CoderConstance.LINE_SEPARATOR + 
								"3. Returning" + 
								CoderConstance.LINE_SEPARATOR + 
								"4. Usage Situation" + 
								CoderConstance.LINE_SEPARATOR + 
								"5. See you" + 
								CoderConstance.LINE_SEPARATOR + 
								"==================================");
	        System.out.println("Enter an option: ");
	        
	        try {
	        		Scanner scanner = new Scanner(System.in);
	        		option = scanner.next().charAt(0);
	        		System.out.println();
	        		
	        		switch (option) {
	        			//+++++++++++++++++++++ 
	        			// Library Information
	        			//+++++++++++++++++++++ 
						case CoderConstance.INFO:
							System.out.println( "==================================" + 
												CoderConstance.LINE_SEPARATOR +
												CoderConstance.LINE_SEPARATOR +
												"----- HOURS -----" + 
												CoderConstance.LINE_SEPARATOR + 
												CoderConstance.WEEKDAYS_HOURS + 
												CoderConstance.LINE_SEPARATOR +
												CoderConstance.WEEKENDS_HOURS + 
												CoderConstance.LINE_SEPARATOR +
												CoderConstance.LINE_SEPARATOR +
												"----- CLOSUER -----" + 
												CoderConstance.LINE_SEPARATOR +
												CoderConstance.CLOSUER + 
												CoderConstance.LINE_SEPARATOR +
												CoderConstance.LINE_SEPARATOR +
												"----- BORROWING -----" + 
												CoderConstance.LINE_SEPARATOR +
												CoderConstance.BORROW + 
												CoderConstance.LINE_SEPARATOR +
												CoderConstance.LINE_SEPARATOR +
												"==================================");
							System.out.println();
							break;
							
						//+++++++++++++++++++++ 
						// Borrowing books
						//+++++++++++++++++++++ 
						case CoderConstance.BORROWING:
							int sumBorrow = bookHashMap.values().stream().mapToInt(Integer::intValue).sum();
							//check the number of books user are borrowing now
							if(sumBorrow < 10) {
								System.out.println( "=================================="  + 
													CoderConstance.LINE_SEPARATOR +
													"How many books do you want to borrow? :");
								
								for(Map.Entry<String, Integer> entry : bookHashMap.entrySet()) {
									// show a key from bookHashMap
									System.out.println(entry.getKey() + ": ");
									int select = scanner.nextInt();
									
									//check if user can borrow books
									if(select + sumBorrow <= 10) {
										//check the number of books exceeds or not the number of books user can borrow
										if( 10 - sumBorrow < select)
											//change the number as much as user can borrow
											select = 10 - sumBorrow;
										//add books 
										bookHashMap.put(entry.getKey(), entry.getValue() + select);
										
										//calculate the total number of borrowing books
										sumBorrow = bookHashMap.values().stream().mapToInt(Integer::intValue).sum();
									}else {
										//borrow as much as user can borrow
										bookHashMap.put(entry.getKey(), 10 - sumBorrow);
										break;
									}
									// reach max 10 books
									if(sumBorrow > 10) 
										break;
								}
							}
							System.out.println( "The number of books lent has reached 10." + 
												CoderConstance.LINE_SEPARATOR +
												CoderConstance.MSG_1 + 
												CoderConstance.LINE_SEPARATOR +
												"==================================");
							break;
							
						//+++++++++++++++++++++ 
						// returning books
						//+++++++++++++++++++++ 
						case CoderConstance.RETURN:
							int sumReturn = bookHashMap.values().stream().mapToInt(Integer::intValue).sum();
							
							if(sumReturn > 0) {
								System.out.println( "=================================="  + 
													CoderConstance.LINE_SEPARATOR +
													"How many books do you want to return? :");
								for(Map.Entry<String, Integer> entry : bookHashMap.entrySet()) {
									//check to see if a number except 0 is in each category
									if(entry.getValue() > 0)
									{
										System.out.println(entry.getKey() + ": ");
										int select = scanner.nextInt();
										
										//change the number of books if user enter a number bigger than sum
										if(entry.getValue() < select) 
											//put 0 into a selected category
											bookHashMap.put(entry.getKey(), 0);
										else
											//put a number into a selected category
											bookHashMap.put(entry.getKey(), (entry.getValue() - select));
									}
								}						
							// there is no borrowing books	
							}
								System.out.println( "Number books of Each category has been checked. " + 
													CoderConstance.LINE_SEPARATOR + 
													CoderConstance.MSG_1 +
													CoderConstance.LINE_SEPARATOR + 
													"==================================");
							break;
							
						//+++++++++++++++++++++ 
						// now book status 
						//+++++++++++++++++++++ 
						case CoderConstance.STATUS:
							// sum of books
							int sum = bookHashMap.values().stream().mapToInt(Integer::intValue).sum();
							
							System.out.println("==================================");
							if(sum > 0) {
								System.out.println( "Number of books you are borrowing..." + 
													CoderConstance.LINE_SEPARATOR);
								for(Map.Entry<String, Integer> entry : bookHashMap.entrySet()) {
                        			System.out.println(entry.getKey() + ": " + entry.getValue());
                        		}
		                        System.out.println( CoderConstance.LINE_SEPARATOR + 
		                        					"Number of books available for borrowing: " + (10 - sum));
							}else {
								System.out.println("==================================");
		                        System.out.println( "Number of books you are borrowing: 0" + 
		                        					CoderConstance.LINE_SEPARATOR +
		                        					"Number of books available for borrowing: " + 10);
							}
							System.out.println("==================================");
	                        System.out.println();
							break;
							
						//+++++++++++++++++++++ 
						// system close
						//+++++++++++++++++++++ 
						case CoderConstance.SEEYOU:
							System.out.println("==================================");
							scanner.close();
							break;
							
						//+++++++++++++++++++++  
						// default
						//+++++++++++++++++++++ 
						default:
							System.out.println("Invaild Number. Please enter again.");
							break;
					}
				} catch (Exception e) {
					System.out.println( "Malfunction occurred. " + 
										CoderConstance.LINE_SEPARATOR +
										"Please start over from the menu.");
				}
			}while(option != '5');
				System.out.println();
				System.out.println( "Thank you for using."  + 
			    					CoderConstance.LINE_SEPARATOR +
			    					"We look forward to serving you again!");
	}
}
