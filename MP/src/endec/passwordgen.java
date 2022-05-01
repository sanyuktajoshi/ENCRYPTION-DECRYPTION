package endec;
import java.util.Random;
//import java.io.IOException;

public class passwordgen {
    
	public static String generateValidPassword(){
		Random rand = new Random();
        String s = "", s_temp;

        for(int i = 0; i < 1024; i++){
            int k = rand.nextInt(999);
            s_temp = Integer.toString(k);
            
            while(s_temp.length()<3){
                s_temp = "0" + s_temp;
            }

            System.out.println(s_temp);    
            s += s_temp;
        }   
        System.out.println(s);
        return s;
    }
	

}
