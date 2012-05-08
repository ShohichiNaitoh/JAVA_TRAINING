package ch05.ex05_02;

public class BankAccount {

	private long number;
	private long balance;
	private History history = new History();

	public class History{
		private final int historyLength = 10;
		private Action[] actions = new Action[historyLength];
		private int latestIndex = 0;
		private int nextIndex = 0;

		public History(){
			for(int i=0 ; i<actions.length ; i++){
				actions[i] = null;
			}
		}

		public void add(Action action){
			if(action == null){
				throw new IllegalArgumentException("action is null.");
			}

			if(latestIndex < historyLength){
				actions[latestIndex++] = action;
			}else{
				for(int i=0 ; i<historyLength-1 ; i++){
					actions[i] = actions[i+1];
				}
				actions[historyLength-1] = action;
			}
		}

		public Action next(){
			Action action = null;
			if(nextIndex < historyLength){
				action = actions[nextIndex];

				if(action == null){
					nextIndex = 0;
				}else{
					nextIndex++;
				}
			}
			return action;
		}
	}

	public class Action{
		private String act;
		private long amount;

		Action(String act , long amount){
			this.act = act;
			this.amount = amount;
		}

		public String toString(){
			return number + ": " + act + " " + amount;
		}
	}

	public void deposit(long amount){
		balance += amount;
		history.add(new Action("deposit" , amount));
	}

	public void withdraw(long amount){
		balance -= amount;
		history.add(new Action("withdraw" , amount));
	}

	public void transfer(BankAccount other , long amount){
		other.withdraw(amount);
		deposit(amount);
		other.history.add(new Action("transfer" , amount));
		history.add(new Action("transfer" , amount));
	}

	public History history(){
		return history;
	}
}
