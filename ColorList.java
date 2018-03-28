import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

class ColorList{
	//ColorList colors = new ColorList();
	//Color aColor = colors.colList.get(12);
	public ArrayList <java.awt.Color> colList = new ArrayList <java.awt.Color> ();
	ColorList(){
		Random rng = new Random();
		for(int i=0;i<256;i++){
			int red= rng.nextInt(256);
			int grn= rng.nextInt(256);
			int blu= rng.nextInt(256);
			Color c = new Color(red,grn,blu);
			colList.add(c);
		}
	}
}

