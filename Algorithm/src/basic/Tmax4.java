package basic;

import java.util.HashMap;

public class Tmax4 {
	
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
		
		
		/*�ϴ� format�� �����̽��� �ڸ��� format ������� for�� ���鼭 
		 * 1. format �� �ʿ����� �ƴ��� üũ
		 * 2.5. format�� "�� �����ϴ��� üũ "�� �پ����� - 
		 * 2. �ȿ��� for�� ������ data���� space üũ ù��° space �� index Ȯ���ؼ� �� ������ substring���� �߶� ���� map�� ���� / �� �ʿ��� ���̸� �׳� index �� ���� �ֱ�
		 * 2.7. format�� "�� �����ϸ�  data���� space �� �ƴ϶� �ι�° " ������ ������ ã�Ƽ� substring ���� �߶� ���� map �� ���� / �� �ʿ��� ���̸� �׳� index�� ���� �ֱ�
         */		
 
	}

}
