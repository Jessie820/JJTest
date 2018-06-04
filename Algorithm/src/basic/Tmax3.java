package basic;

import java.util.HashMap;
import java.util.Map;

public class Tmax3 {

	
	static HashMap<String,String> rule = new HashMap<String, String>() //A: 필요한 포맷 - 포맷에 해당하는 정보
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
		 * %가 몇 개인지 센다. 1.순서 & 2.해당하는 의미 를 HashMap에 담는다.
		 * 그냥 구분자로 가능한 것들을 몇 개 정해주고 그 몇 개에 해당하는 문자를 만나면 거기서 한 단어를 정한다...?
		*/
		//String format = "%{host}i %h %l %u %t \"%r\" %s %b %D";
		//String format = "%{host}i %h %t \"%r\" %s %b $D \"%{User-Agent}i\"";
		String format = "%{AGENT_HOME}e \"%r\" %s %b %{host}i %l %h %D %u %t";
		String[] format_opt = {" "};
		//String data = "192.168.13.44:8080 192.168.13.43 - - [18/May/2018:13:41:51 +0900] \"POST /sysmaster/admin/requestDummy.action HTTP/1.1\" 200 2 4";
		//String data="192.168.13.44:8080 192.168.13.43 \"[02/May/2018:15:26:42 +0900]\" \"GET /sysmaster/base/login.action HTTP/1.1\" 200 6309 114 \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36\"";
		String data="/home/claire/agent \"GET /sysmaster/resource/base/script/bundle/base/Messages.properties?_=1527074918500 HTTP/1.1\" 200 2722 192.168.13.44:8089 - 192.168.13.43 0 - [23/May/2018:20:29:14 +0900]";
		
		//[nodeName=claire-System-Product-Name, virtualHost=192.168.13.44:8080, vhostCode=-710490531, remoteHost=192.168.13.43, remoteLog=-, user=-, logDate=20180518134151, method=POST, requestUri=/sysmaster/admin/requestDummy.action, uriCode=1441896060, httpVer=HTTP/1.1, status=200, bytes=2, respTime=4];
	
	
		HashMap<Integer, String> divisor = new HashMap<Integer, String>();//B: 필요한 정보 - 구분자 (A를 이용해 담는다)
		
		HashMap<String,String> accesslog = new HashMap<String, String>();//C: 필요한 정보 - 실데이터 (B를 이용해 담는다)
		
		format = format.trim();
		data = data.trim();
		
		int num=0;
		
		for(int i=0; i<format.length()-1; i++){ // format.length()-1 만큼 도니까 out of index 에러가 날 일은 없겠지..?
	
			StringBuffer sb;
			String checkRule="";//A테이블에서 찾을 Key를 저장할 변수
			
			if(format.charAt(i) == '%' && format.charAt(i+1) != '{'){ //그냥 한 글자를 checkRule에 저장
				checkRule = String.valueOf(format.charAt(i+1));	
				num++;
			}else if(format.charAt(i) == '%' && format.charAt(i+1) == '{'){//{로 시작하는 포맷이면 }을 찾을 때까지 찾는다.
				num++;
				int j=0;
				sb = new StringBuffer();
				for(j=i+1; j<format.length()-1; j++){
					if(format.charAt(j)=='}'){//}을 찾으면 }와 함께 그 다음 글자도 붙여서 checkRule에 저장
						sb.append(format.charAt(j));
						sb.append(format.charAt(j+1));
						break;
					}else{
						sb.append(format.charAt(j));
					}
				}
				
				checkRule = sb.toString(); //전체 포맷을 checkRule에 저장
				i=j+1;
			}
			
			
			if(rule.get(checkRule) != null){//포맷이 A테이블에 있으면 해당하는 정보와 순서를 함께 저장
			     divisor.put(num, rule.get(checkRule));
			}else{//없으면 trash라고 표기하고 순서를 함께 저장
				//divisor.put(num++,"trash");	
			}
			
		}
		
		
		//여기서 포맷에 해당하는 걸 다 못찾았으면 여기서 바로 에러를 낼 수도 있는데....흠...이건 어떻게 찾지...?
		//어근데 에러를 내면 안되나...? 뭐 고객사에서 자기네는 저기에 필요한 정보 다 안받을수도 있다고 생각하면...? 이건 좀 더 찾아보기
		
		//"이나 [만나면 그다음 "이나 ]을 만날때까지 공백을 체크하면 안되는뎅
		
		//항상 한 줄씩 로그가 오는 것도 아니고 중간에 짤린다고 한 거 같은데...ㅜㅜ 윽
		
		boolean flag = true; // 첫 공백인지 체크용
		
		boolean flag2 = false; // " 체크, "이 있을 때 []은 무시
		boolean flag3 = false; // [체크
		
		
		//일단 [ 나 " 들어간 애들을 찾아서 묶은 다음 스페이스로 나눌까...? 그럴수 있나...?
		
		
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
		
		if(p == ' '){//일단 구분자는 스페이스 하나라고 생각하고 체크했음
			return true;
		}else{
			return false;
		}
		
	}
	

}
