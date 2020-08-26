package com.sist.manager;
/*
 *  mno NUMBER,
	cateno NUMBER,
	title VARCHAR2(300) CONSTRAINT music_title_nn NOT NULL,
	poster VARCHAR2(260) CONSTRAINT music_poster_nn NOT NULL,
	singer VARCHAR2(100) CONSTRAINT music_singer_nn NOT NULL,
	album VARCHAR2(100) CONSTRAINT music_album_nn NOT NULL,
	CONSTRAINT music_mno_pk PRIMARY KEY(mno),
	CONSTRAINT music_cateno_fk FOREIGN KEY(cateno) REFERENCES music_genre(no)
 * 
 */
public class MusicVO {

		private int mno;
		private int cateno;
		private String title;
		private String poster;
		private String singer;
		private String album;
		private int rank;
		
		
		public int getRank() {
			return rank;
		}
		public void setRank(int rank) {
			this.rank = rank;
		}
		public int getCateno() {
			return cateno;
		}
		public void setCateno(int cateno) {
			this.cateno = cateno;
		}
		public int getMno() {
			return mno;
		}
		public void setMno(int mno) {
			this.mno = mno;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getPoster() {
			return poster;
		}
		public void setPoster(String poster) {
			this.poster = poster;
		}
		public String getSinger() {
			return singer;
		}
		public void setSinger(String singer) {
			this.singer = singer;
		}
		public String getAlbum() {
			return album;
		}
		public void setAlbum(String album) {
			this.album = album;
		}
}
