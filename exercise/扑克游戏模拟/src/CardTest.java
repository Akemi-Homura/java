
public class CardTest {
	public static void main(String[] args) {
		GameManger item = GameManger.getInstance();
		item.initGame();
		item.addPlayer("Homura");
		item.addPlayer("xll");
		item.addPlayer("hqq");
		item.addPlayer("James Joyce");
		item.initPlayerCards();
		item.deliverCard("xll");
		try{
			for (int i = 0; i < item.getPlayerNum(); i++) {
				item.showPlayerCards(item.getPlayerName(i));
			}
		}catch(IndexOutOfBoundsException ex){
			System.out.println("数组越界！");
		}
	}
}
