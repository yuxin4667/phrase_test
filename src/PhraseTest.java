import java.util.*;

public class PhraseTest 
{
	static Scanner sc=new Scanner(System.in);
	public static void mode1(String ques[], String ans[], int record[], int error[], int quesnum, int score)
	{
		String input = new String();//user輸入的答案
		int []temp=new int[20];//儲存錯題
		//初始化是否出過某題及錯題陣列
		for(int a=0;a<20;a++)
		{
			record[a]=0;
			temp[a]=21;
		}
		int number, i=0, j=0, k=0;
		
		//先從錯題陣列中出題，error[k]為題號
		for(k=0;error[k]!=21 && k<quesnum;k++)
		{
			System.out.print("第"+(i+1)+"題- "+ques[error[k]]+": ");
			input=sc.nextLine();
			
			if(input.equals(ans[error[k]]))//答對
			{
				score+=10;
				System.out.println("CORRECT! 目前得分: "+score+"分");
			}
			else//答錯
			{
				System.out.println("INCORRECT! 目前得分: "+score+"分");
				temp[j]=error[k];//錯題題號紀錄至錯題陣列中
				j++;//錯題陣列索引+1
			}
			record[error[k]]=1;//更新是否出過某題的陣列
		}
		
		//更新錯題名單
		for(int a=0;a<20;a++)
		{
			int b=0;
			error[a]=21;
			if(temp[a]!=21)
			{
				error[b]=temp[a];
				b++;
			}
		}
		
		//若錯題出完了，從剩餘的題目中出題
		while(i<quesnum-k)
		{
			number = (int)(Math.random()*1000) % 19;//隨機題號
			if(record[number]==0)//判斷此題尚未出過
			{
				System.out.print("第"+(i+1)+"題- "+ques[number]+": ");
				input=sc.nextLine();
				if(input.equals(ans[number]))//答對
				{
					score+=10;
					System.out.println("CORRECT! 目前得分: "+score+"分");
				}
				else//答錯
				{
					System.out.println("INCORRECT! 目前得分: "+score+"分");
					error[j++]=number;//錯題題號紀錄至錯題陣列中
				}
				
				record[number]=1;//更新是否出過某題的陣列
				i++;//記錄此輪的總出題數
			}
			else//判斷此題已經出過
				continue;//重新隨機數字
		}
		System.out.println("測驗結束! 得分:"+score+"分, 答對率"+(((quesnum-j)*100)/quesnum)+"%");
		System.out.println("======================================");
	}
	
	public static void mode2(String ques[], String ans[], int record[], int error[], int quesnum, int score)
	{
		String input = new String();//user輸入的答案
		int []temp=new int[20];//儲存錯題
		int []getscore=new int[20];//紀錄該小題可得分數
		//初始化是否出過某題及錯題陣列
		for(int a=0;a<20;a++)
		{
			record[a]=0;
			temp[a]=21;
			getscore[a]=10;
		}
		int number, i=0, j=0, k=0;
		
		//先從錯題陣列中出題，error[k]為題號
		for(k=0;error[k]!=21 && k<quesnum;k++)
		{
			record[error[k]]=1;//更新是否出過某題的陣列
			i++;//記錄此輪的總出題數
			while(true)
			{
				System.out.print("第"+i+"題- "+ques[error[k]]+": ");
				input=sc.nextLine();
				if(input.equals(ans[error[k]]))//答對
				{
					score+=getscore[error[k]];
					System.out.println("CORRECT! 目前得分: "+score+"分");
					break;
				}
				else//答錯
				{
					if(getscore[error[k]]==10)
						error[j++]=error[k];//錯題題號紀錄至錯題陣列中
					if(getscore[error[k]]>0)
						getscore[error[k]]--;
					System.out.println("INCORRECT! Hint:"+ans[error[k]].charAt(0)+"..."+ans[error[k]].charAt(ans[error[k]].length()-1));
				}
			}
		}
		
		//更新錯題名單
		for(int a=0;a<20;a++)
		{
			int b=0;
			error[a]=21;
			if(temp[a]!=21)
			{
				error[b]=temp[a];
				b++;
			}
		}

		//若錯題出完了，從剩餘的題目中出題
		while(i<quesnum)
		{
			number = (int)(Math.random()*1000) % 19;//隨機題號
			if(record[number]==0)//判斷此題尚未出過
			{
				record[number]=1;//更新是否出過某題的陣列
				i++;//記錄此輪的總出題數
				while(true)
				{
					System.out.print("第"+i+"題- "+ques[number]+": ");
					input=sc.nextLine();
					if(input.equals(ans[number]))//答對
					{
						score+=getscore[number];
						System.out.println("CORRECT! 目前得分: "+score+"分");
						break;
					}
					else//答錯
					{
						if(getscore[number]==10)
							error[j++]=number;//錯題題號紀錄至錯題陣列中
						if(getscore[number]>0)
							getscore[number]--;
						System.out.println("INCORRECT! Hint:"+ans[number].charAt(0)+"..."+ans[number].charAt(ans[number].length()-1));
					}
				}
			}
			else//判斷此題已經出過
				continue;//重新隨機數字
		}
		System.out.println("測驗結束! 得分:"+score+"分, 答對率"+(((quesnum-j)*100)/quesnum)+"%");
		System.out.println("======================================");
	}
	
	
	public static void main(String[] args) 
	{	
		String []ans={"in no time", "by all means", "get over", "compete for", "give way to", 
				"make up for", "free of", "long before", "vary with", "bring about",
				"accompany by", "pass through", "reach for", "contrary to", "except for", 
				"more of", "other than", "call for", "try out", "set up"};
		String []ques={"馬上;立刻", "好的;當然", "克服;恢復常態", "競爭", "讓位於;向…屈服", 
				"彌補", "免除;擺脫", "很久以前", "隨…變化", "造成;引起", 
				"伴隨;陪同", "經過", "伸手拿", "與…相反", "除了…以外", 
				"更大程度上的;更多的", "而不…", "需要;值得;應該", "參加(運動隊的)選拔;參加(劇組的)試演", "設置"};
		int []record=new int[20];
		int []error=new int[20];
		for(int a=0;a<20;a++)
		{
			error[a]=21;
		}
		int mode, quesnum=0, score=0, again;
		String getnewline=new String();
		
		while(true)
		{
			score=0;
			quesnum=0;
			System.out.print("請選擇測驗模式 1.純測驗模式 2.練習+提示模式:");
			mode=sc.nextInt();//讀取測驗模式
			getnewline=sc.nextLine();
			if(!(mode==1||mode==2))
			{
				System.out.println("輸入錯誤!");
				continue;
			}
			else
			{
				while(quesnum<=0 || quesnum>20)//輸入題數
				{
					System.out.print("請輸入測驗題數(<=20):");
					quesnum=sc.nextInt();
					getnewline=sc.nextLine();
				}
			}
			switch(mode)
			{
			case 1:
				mode1(ques, ans, record, error, quesnum, score);//1.純測驗模式
				break;
			case 2:
				mode2(ques, ans, record, error, quesnum, score);//2.練習+提示模式
				break;
			default:
				break;
			}
			
			System.out.print("請選擇 1.再次測驗 2.離開:");
			again=sc.nextInt();
			if(again==1)
				continue;
			else
			{
				System.out.println("Bye!");
				break;
			}
		}
		sc.close();
	}
}
