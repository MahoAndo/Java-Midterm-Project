/**
 * @author maho ando
 * @date Oct 23, 2022
 *
 */
public class Main {

	/**
	 * Library Machine CLI
	 */
	public static void main(String[] args) {		
		LibrarySystem<String> myLibrary =	new LibrarySystem<>("Maho");
		// call sLibraryAccount method
		myLibrary.showMenu();
	}
}
