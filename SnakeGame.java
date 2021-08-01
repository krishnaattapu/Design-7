import java.util.Arrays;
import java.util.LinkedList;

public class SnakeGame {
	private LinkedList<int[]> snake;
	private LinkedList<int[]> foodList;
	private int[] snakeHead;
	private int height;
	private int width;

	public SnakeGame(int width, int height, int[][] food) {
		this.width=width;
		this.height=height;
		foodList=new LinkedList<>(Arrays.asList(food));
		snakeHead = new int[] {0,0};
		snake=new LinkedList<>();
		snake.add(snakeHead);
	}

	public int move(String direction) {
		if(direction.equals("U")) snakeHead[0]--;
		else if(direction.equals("L")) snakeHead[1]--;
		else if(direction.equals("R")) snakeHead[1]++;
		else if(direction.equals("D")) snakeHead[0]++;
		
		//boundary check
		if(snakeHead[0]==height||snakeHead[0]<0 || snakeHead[1]==width || snakeHead[1]<0)
			return -1;
		
		//hit itself
		for(int i=1;i<snake.size();i++) {
			int curr[]=snake.get(i);
			if(snakeHead[0]==curr[0] && snakeHead[1]==curr[1])
				return -1;
		}
		
		//eat food
		if(!foodList.isEmpty()) {
			int[] foodappear=foodList.get(0);
			if(snakeHead[0]==foodappear[0] && snakeHead[1]==foodappear[1]) {
				snake.add(foodList.remove());
				return snake.size()-1;
			}
		}
		
		//move
		int[] newCell=new int[] {snakeHead[0],snakeHead[1]};
		snake.add(newCell);
		snake.remove();
		return snake.size()-1;
	}
}
