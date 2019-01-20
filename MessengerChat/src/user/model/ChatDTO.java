package user.model;

import java.io.Serializable;

public class ChatDTO implements Serializable{
		private int chatNo;
		private String fromID;
		private String toID;
		private String chatContent;
		private String chatTime;
		
		public ChatDTO(){}

		public ChatDTO(int chatNo, String fromID, String toID, String chatContent, String chatTime) {
			super();
			this.chatNo = chatNo;
			this.fromID = fromID;
			this.toID = toID;
			this.chatContent = chatContent;
			this.chatTime = chatTime;
		}

		public int getChatNo() {
			return chatNo;
		}
		public void setChatNo(int chatNo) {
			this.chatNo = chatNo;
		}
		public String getFromID() {
			return fromID;
		}
		public void setFromID(String fromID) {
			this.fromID = fromID;
		}
		public String getToID() {
			return toID;
		}
		public void setToID(String toID) {
			this.toID = toID;
		}
		public String getChatContent() {
			return chatContent;
		}
		public void setChatContent(String chatContent) {
			this.chatContent = chatContent;
		}
		public String getChatTime() {
			return chatTime;
		}
		public void setChatTime(String chatTime) {
			this.chatTime = chatTime;
		}
		@Override
		public String toString() {
			return "ChatDTO [chatNo=" + chatNo + ", fromID=" + fromID + ", toID=" + toID + ", chatContent="
					+ chatContent + ", chatTime=" + chatTime + "]";
		}
}
