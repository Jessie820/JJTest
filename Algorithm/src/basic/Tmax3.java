package basic;

import java.util.HashMap;
import java.util.Map;

public class Tmax3 {

	
	static HashMap<String,String> rule = new HashMap<String, String>() //A: �ʿ��� ���� - ���˿� �ش��ϴ� ����
	{{
		put("{host}i", "virtualHost");
		put("h", "remoteHost");
		put("l", "remoteLog");
		put("u", "user");
		put("t", "logDate");
		put("r", "mrh");
		put("s", "status");
		put("b", "bytes");
		put("D", "respTime");

	}};
	
	public static void main(String[] args) {
		/*
		 * %�� �� ������ ����. 1.���� & 2.�ش��ϴ� �ǹ� �� HashMap�� ��´�.
		 * �׳� �����ڷ� ������ �͵��� �� �� �����ְ� �� �� ���� �ش��ϴ� ���ڸ� ������ �ű⼭ �� �ܾ ���Ѵ�...?
		*/
		//String format = "%{host}i %h %l %u %t \"%r\" %s %b %D";
		//String format = "%{host}i %h %t \"%r\" %s %b $D \"%{User-Agent}i\"";
		String format = "%{AGENT_HOME}e \"%r\" %s %b %{host}i %l %h %D %u %t";
		String[] format_opt = {" "};
		//String data = "192.168.13.44:8080 192.168.13.43 - - [18/May/2018:13:41:51 +0900] \"POST /sysmaster/admin/requestDummy.action HTTP/1.1\" 200 2 4";
		//String data="192.168.13.44:8080 192.168.13.43 \"[02/May/2018:15:26:42 +0900]\" \"GET /sysmaster/base/login.action HTTP/1.1\" 200 6309 114 \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36\"";
		String data="/home/claire/agent \"GET /sysmaster/resource/base/script/bundle/base/Messages.properties?_=1527074918500 HTTP/1.1\" 200 2722 192.168.13.44:8089 - 192.168.13.43 0 - [23/May/2018:20:29:14 +0900]";
		
		//[nodeName=claire-System-Product-Name, virtualHost=192.168.13.44:8080, vhostCode=-710490531, remoteHost=192.168.13.43, remoteLog=-, user=-, logDate=20180518134151, method=POST, requestUri=/sysmaster/admin/requestDummy.action, uriCode=1441896060, httpVer=HTTP/1.1, status=200, bytes=2, respTime=4];
	
	
		HashMap<Integer, String> divisor = new HashMap<Integer, String>();//B: �ʿ��� ���� - ������ (A�� �̿��� ��´�)
		
		HashMap<String,String> accesslog = new HashMap<String, String>();//C: �ʿ��� ���� - �ǵ����� (B�� �̿��� ��´�)
		
		format = format.trim();
		data = data.trim();
		
		int num=0;
		
		for(int i=0; i<format.length()-1; i++){ // format.length()-1 ��ŭ ���ϱ� out of index ������ �� ���� ������..?
	
			StringBuffer sb;
			String checkRule="";//A���̺��� ã�� Key�� ������ ����
			
			if(format.charAt(i) == '%' && format.charAt(i+1) != '{'){ //�׳� �� ���ڸ� checkRule�� ����
				checkRule = String.valueOf(format.charAt(i+1));	
				num++;
			}else if(format.charAt(i) == '%' && format.charAt(i+1) == '{'){//{�� �����ϴ� �����̸� }�� ã�� ������ ã�´�.
				num++;
				int j=0;
				sb = new StringBuffer();
				for(j=i+1; j<format.length()-1; j++){
					if(format.charAt(j)=='}'){//}�� ã���� }�� �Բ� �� ���� ���ڵ� �ٿ��� checkRule�� ����
						sb.append(format.charAt(j));
						sb.append(format.charAt(j+1));
						break;
					}else{
						sb.append(format.charAt(j));
					}
				}
				
				checkRule = sb.toString(); //��ü ������ checkRule�� ����
				i=j+1;
			}
			
			
			if(rule.get(checkRule) != null){//������ A���̺� ������ �ش��ϴ� ������ ������ �Բ� ����
			     divisor.put(num, rule.get(checkRule));
			}else{//������ trash��� ǥ���ϰ� ������ �Բ� ����
				//divisor.put(num++,"trash");	
			}
			
		}
		
		
		//���⼭ ���˿� �ش��ϴ� �� �� ��ã������ ���⼭ �ٷ� ������ �� ���� �ִµ�....��...�̰� ��� ã��...?
		//��ٵ� ������ ���� �ȵǳ�...? �� ���翡�� �ڱ�״� ���⿡ �ʿ��� ���� �� �ȹ������� �ִٰ� �����ϸ�...? �̰� �� �� ã�ƺ���
		
		//"�̳� [������ �״��� "�̳� ]�� ���������� ������ üũ�ϸ� �ȵǴµ�
		
		//�׻� �� �پ� �αװ� ���� �͵� �ƴϰ� �߰��� ©���ٰ� �� �� ������...�̤� ��
		
		boolean flag = true; // ù �������� üũ��
		
		boolean flag2 = false; // " üũ, "�� ���� �� []�� ����
		boolean flag3 = false; // [üũ
		
		
		//�ϴ� [ �� " �� �ֵ��� ã�Ƽ� ���� ���� �����̽��� ������...? �׷��� �ֳ�...?
		
		
		StringBuffer divided = new StringBuffer();
		int wordCount=0;
		for(int i=0; i<data.length(); i++){
			
			//if(data.charAt(i)==' '&& flag){
			if(data.charAt(i) == '\"'){
			  if(!flag2){	
				flag2 = true;	
			  }else{
				flag2 = false;
			  }
			}
			
			if(data.charAt(i) == '[' && !flag2){
					flag3 = true;	
			}
			
			if(data.charAt(i) == ']' && !flag2){
				flag3 = false;	
		}
			
			if(checkDivsor(data.charAt(i)) && flag && !flag2 && !flag3){	
				flag = false;
				++wordCount;
				
				if(divisor.get((Integer)wordCount)!=null){
				  accesslog.put(divisor.get((Integer)wordCount), divided.toString());
				}
				
				divided = new StringBuffer();
				
			//}else if(data.charAt(i)!=' '){
			}else if(!checkDivsor(data.charAt(i)) || flag2 || flag3){
				flag = true;
				
				if(data.charAt(i) != '\"' &&  data.charAt(i) != '[' && data.charAt(i) != ']'){
		
						divided.append(data.charAt(i));
					
				}
			}
				
		}
		
		wordCount +=1;
		
		if(divisor.get((Integer)wordCount)!=null){
			  accesslog.put(divisor.get((Integer)wordCount), divided.toString());
		}
		
		for(Map.Entry<Integer, String> entry: divisor.entrySet()){
			System.out.print(entry.getKey()+": "+entry.getValue());
			System.out.println();
		}
		
		System.out.println("------------------------------------------------");
		
		for(Map.Entry<String, String> entry: accesslog.entrySet()){
			System.out.print(entry.getKey()+": "+entry.getValue());
			System.out.println();
		}
	    
	
	}
	
	
	static boolean checkDivsor(char p){
		
		if(p == ' '){//�ϴ� �����ڴ� �����̽� �ϳ���� �����ϰ� üũ����
			return true;
		}else{
			return false;
		}
		
	}
	

}
