import java.util.*;

public class PhraseTest 
{
	static Scanner sc=new Scanner(System.in);
	public static void mode1(String ques[], String ans[], int record[], int error[], int quesnum, int score)
	{
		String input = new String();//user��J������
		int []temp=new int[20];//�x�s���D
		//��l�ƬO�_�X�L�Y�D�ο��D�}�C
		for(int a=0;a<20;a++)
		{
			record[a]=0;
			temp[a]=21;
		}
		int number, i=0, j=0, k=0;
		
		//���q���D�}�C���X�D�Aerror[k]���D��
		for(k=0;error[k]!=21 && k<quesnum;k++)
		{
			System.out.print("��"+(i+1)+"�D- "+ques[error[k]]+": ");
			input=sc.nextLine();
			
			if(input.equals(ans[error[k]]))//����
			{
				score+=10;
				System.out.println("CORRECT! �ثe�o��: "+score+"��");
			}
			else//����
			{
				System.out.println("INCORRECT! �ثe�o��: "+score+"��");
				temp[j]=error[k];//���D�D�������ܿ��D�}�C��
				j++;//���D�}�C����+1
			}
			record[error[k]]=1;//��s�O�_�X�L�Y�D���}�C
		}
		
		//��s���D�W��
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
		
		//�Y���D�X���F�A�q�Ѿl���D�ؤ��X�D
		while(i<quesnum-k)
		{
			number = (int)(Math.random()*1000) % 19;//�H���D��
			if(record[number]==0)//�P�_���D�|���X�L
			{
				System.out.print("��"+(i+1)+"�D- "+ques[number]+": ");
				input=sc.nextLine();
				if(input.equals(ans[number]))//����
				{
					score+=10;
					System.out.println("CORRECT! �ثe�o��: "+score+"��");
				}
				else//����
				{
					System.out.println("INCORRECT! �ثe�o��: "+score+"��");
					error[j++]=number;//���D�D�������ܿ��D�}�C��
				}
				
				record[number]=1;//��s�O�_�X�L�Y�D���}�C
				i++;//�O���������`�X�D��
			}
			else//�P�_���D�w�g�X�L
				continue;//���s�H���Ʀr
		}
		System.out.println("���絲��! �o��:"+score+"��, ����v"+(((quesnum-j)*100)/quesnum)+"%");
		System.out.println("======================================");
	}
	
	public static void mode2(String ques[], String ans[], int record[], int error[], int quesnum, int score)
	{
		String input = new String();//user��J������
		int []temp=new int[20];//�x�s���D
		int []getscore=new int[20];//�����Ӥp�D�i�o����
		//��l�ƬO�_�X�L�Y�D�ο��D�}�C
		for(int a=0;a<20;a++)
		{
			record[a]=0;
			temp[a]=21;
			getscore[a]=10;
		}
		int number, i=0, j=0, k=0;
		
		//���q���D�}�C���X�D�Aerror[k]���D��
		for(k=0;error[k]!=21 && k<quesnum;k++)
		{
			record[error[k]]=1;//��s�O�_�X�L�Y�D���}�C
			i++;//�O���������`�X�D��
			while(true)
			{
				System.out.print("��"+i+"�D- "+ques[error[k]]+": ");
				input=sc.nextLine();
				if(input.equals(ans[error[k]]))//����
				{
					score+=getscore[error[k]];
					System.out.println("CORRECT! �ثe�o��: "+score+"��");
					break;
				}
				else//����
				{
					if(getscore[error[k]]==10)
						error[j++]=error[k];//���D�D�������ܿ��D�}�C��
					if(getscore[error[k]]>0)
						getscore[error[k]]--;
					System.out.println("INCORRECT! Hint:"+ans[error[k]].charAt(0)+"..."+ans[error[k]].charAt(ans[error[k]].length()-1));
				}
			}
		}
		
		//��s���D�W��
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

		//�Y���D�X���F�A�q�Ѿl���D�ؤ��X�D
		while(i<quesnum)
		{
			number = (int)(Math.random()*1000) % 19;//�H���D��
			if(record[number]==0)//�P�_���D�|���X�L
			{
				record[number]=1;//��s�O�_�X�L�Y�D���}�C
				i++;//�O���������`�X�D��
				while(true)
				{
					System.out.print("��"+i+"�D- "+ques[number]+": ");
					input=sc.nextLine();
					if(input.equals(ans[number]))//����
					{
						score+=getscore[number];
						System.out.println("CORRECT! �ثe�o��: "+score+"��");
						break;
					}
					else//����
					{
						if(getscore[number]==10)
							error[j++]=number;//���D�D�������ܿ��D�}�C��
						if(getscore[number]>0)
							getscore[number]--;
						System.out.println("INCORRECT! Hint:"+ans[number].charAt(0)+"..."+ans[number].charAt(ans[number].length()-1));
					}
				}
			}
			else//�P�_���D�w�g�X�L
				continue;//���s�H���Ʀr
		}
		System.out.println("���絲��! �o��:"+score+"��, ����v"+(((quesnum-j)*100)/quesnum)+"%");
		System.out.println("======================================");
	}
	
	
	public static void main(String[] args) 
	{	
		String []ans={"in no time", "by all means", "get over", "compete for", "give way to", 
				"make up for", "free of", "long before", "vary with", "bring about",
				"accompany by", "pass through", "reach for", "contrary to", "except for", 
				"more of", "other than", "call for", "try out", "set up"};
		String []ques={"���W;�ߨ�", "�n��;��M", "�J�A;��_�`�A", "�v��", "�����;�V�K�}�A", 
				"����", "�K��;�\��", "�ܤ[�H�e", "�H�K�ܤ�", "�y��;�ް_", 
				"���H;���P", "�g�L", "���⮳", "�P�K�ۤ�", "���F�K�H�~", 
				"��j�{�פW��;��h��", "�Ӥ��K", "�ݭn;�ȱo;����", "�ѥ[(�B�ʶ���)���;�ѥ[(�@�ժ�)�պt", "�]�m"};
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
			System.out.print("�п�ܴ���Ҧ� 1.�´���Ҧ� 2.�m��+���ܼҦ�:");
			mode=sc.nextInt();//Ū������Ҧ�
			getnewline=sc.nextLine();
			if(!(mode==1||mode==2))
			{
				System.out.println("��J���~!");
				continue;
			}
			else
			{
				while(quesnum<=0 || quesnum>20)//��J�D��
				{
					System.out.print("�п�J�����D��(<=20):");
					quesnum=sc.nextInt();
					getnewline=sc.nextLine();
				}
			}
			switch(mode)
			{
			case 1:
				mode1(ques, ans, record, error, quesnum, score);//1.�´���Ҧ�
				break;
			case 2:
				mode2(ques, ans, record, error, quesnum, score);//2.�m��+���ܼҦ�
				break;
			default:
				break;
			}
			
			System.out.print("�п�� 1.�A������ 2.���}:");
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
