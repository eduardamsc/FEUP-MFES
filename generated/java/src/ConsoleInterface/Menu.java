package ConsoleInterface;

public class Menu {
	
	public String[] m_options;
	public String[] m_callbacks;
	public String[][] m_inputs;
	public Menu m_back;
		
	public Menu(String[] options, String[] callbacks, String[][] inputs, Menu back) {
		m_options = options;
		m_callbacks = callbacks;
		m_inputs = inputs;
		m_back = back;
	}
	
	public String toString() {
		String res = new String();
        for(int i = 0; i < m_options.length; i++) 
        	res += i + ". " + m_options[i] + "\n";
        res += "\n" + "Select an option: ";
        return res;
    }
	
	public void run() {
		System.out.println(this);
		int option = getMenuOption();
		String[] args = getInputs(m_inputs[option]);
		MenuFactory.call(m_callbacks[option], args);
	}
	
	public String[] getInputs(String[] inputs) {
		String[] args = new String[inputs.length];
		for(int i = 0; i < inputs.length; i++) {
			System.out.print("Enter " + inputs[i] + ": ");
			args[i] = MenuFactory.scanner.nextLine();
		}
		return args;
	}
	
	public int getMenuOption() {
		int option = -1;
		do {
			String line = MenuFactory.scanner.nextLine();
			try {
				option = Integer.parseInt(line);
			} catch(Exception e) {
				continue;
			}
		} while( option < 0 || option >= m_options.length);
		return option;
	}
	
}
