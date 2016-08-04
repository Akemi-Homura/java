import java.util.*;

public class GameManger {
	// 最大玩家数
	private final int MAX_PLAYER_NUM = 4;
	// 单例模式
	private static GameManger _instance = null;

	private GameManger() {
	}
	// 卡牌总数
	private int totalCards = 54;
	// 花色
	private final String[] types = { "红桃", "方块", "梅花", "黑桃" };
	// 卡牌上的值
	private final String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
	// 总卡牌
	private LinkedList<String> cards = new LinkedList<String>();
	// 玩家名
	private LinkedList<String> players = new LinkedList<String>();
	// 每名玩家的牌
	private LinkedList<String>[] playerCards = new LinkedList[MAX_PLAYER_NUM];

	public static GameManger getInstance() {
		if (_instance == null) {
			_instance = new GameManger();
		}
		return _instance;
	}

	public String getPlayerName(int index) {
		return players.get(index);
	}

	public int getPlayerNum() {
		return players.size();
	}
	// 初始化游戏
	public void initGame() {
		players.clear();
		cards.clear();
		for (int i = 0; i < types.length; i++) {
			for (int j = 0; j < values.length; j++) {
				cards.add(types[i] + values[j]);
			}
		}
		cards.add("Red Joker");
		cards.add("Black Joker");

	}
	// 添加玩家
	public void addPlayer(String name) {
		if (players.contains(name)) {
			System.out.println("此玩家已被添加，请重新输入");
		} else if (players.size() == MAX_PLAYER_NUM) {
			System.out.println("已经有4名玩家，不能再添加");
		} else {
			players.add(name);
		}
	}
	// 初始化玩家卡牌，初始每个玩家的卡牌为数为0
	public void initPlayerCards() {
		for (int i = 0; i < players.size(); i++) {
			if (playerCards[i] == null)
				playerCards[i] = new LinkedList<String>();
			else
				playerCards[i].clear();
		}
		
	}
	// 发牌
	public void deliverCard(String first) {
		// 模拟洗牌的方法
		Collections.shuffle(cards);
		int firstPos = players.indexOf(first);
		if (firstPos == -1)
			firstPos = 0;
		int leftCards = 54;
		while (leftCards > 0) {
			for (int i = firstPos; i < players.size() && leftCards>0; i++) {
				playerCards[i].add(cards.get(totalCards - leftCards--));
			}
			for (int i = 0; i < firstPos && leftCards>0; i++) {
				playerCards[i].add(cards.get(totalCards - leftCards--));
			}
		}
	}
	// 输出每个玩家的牌，调试用
	public void showPlayerCards(String name) {
		int index = players.indexOf(name);
		if (index == -1) {
			System.out.println("当前玩家不存在");
			return;
		}
		System.out.println(name + "'s cards:");
		System.out.println(playerCards[index]);
	}
}
