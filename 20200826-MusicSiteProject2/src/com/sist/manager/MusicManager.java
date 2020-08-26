package com.sist.manager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.dao.MusicDAO;

	public class MusicManager {

		public void MusicAllData() {
		
		MusicDAO dao=new MusicDAO(); // for�� �ٱ��� ����
		
		try {
			
			int k=1;
			
			//for(int i=1; i<=50; i++) 
			{
			
			Document doc=Jsoup.connect("https://www.melon.com/genre/song_list.htm?gnrCode=GN0800#params%5BgnrCode%5D=GN0800&params%5BdtlGnrCode%5D=&params%5BorderBy%5D=NEW&params%5BsteadyYn%5D=N&po=pageObj&startIndex=1").get();
			Elements title=doc.select("div.wrap_song_info  div.rank01 a");
			Elements singer=doc.select("div.wrap_song_info  div.rank02 a:eq(0)");
			Elements album=doc.select("div.wrap_song_info  div.rank03 a");
			Elements poster=doc.select("div.wrap a.image_typeAll img");
			
			
			int m=1;
			for(int j=0; j<title.size(); j++) {
				
				try { // null���� �־ ��� for�� ����
				
				MusicVO vo=new MusicVO();
				
				System.out.println("��ȣ:"+ k++);
				System.out.println("cateno:1");
				System.out.println("����:"+title.get(j).text());
				System.out.println("������:"+singer.get(m).text());
				System.out.println("�ٹ�:"+album.get(j).text());
				System.out.println("������:"+poster.get(j).attr("src"));
				System.out.println("==============================");
				
				m+=2;
				
				// vo�� ���� ä��� => DAO
				vo.setCateno(8); 
				vo.setTitle(title.get(j).text());
				vo.setSinger(singer.get(m).text());
				vo.setAlbum(album.get(j).text());
				vo.setPoster(poster.get(j).attr("src"));
				
				// DAO�� ����
				dao.musicInsert(vo);
				Thread.sleep(100);
				
				} catch (Exception e) {
                    e.printStackTrace();
				}
			}
	
			System.out.println("End...");
			
		    }
			
		} catch (Exception e) {

		}
	
	}
	
	public static void main(String[] args) {

		MusicManager m=new MusicManager();
		m.MusicAllData();
	}
	

}
