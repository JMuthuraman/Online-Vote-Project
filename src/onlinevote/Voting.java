package onlinevote;

public class Voting 
{
  private int voterID;
  private String voterName;
  private String voteremailID;
  private String voterpassword;
  
  Voting( String voterName, int voterID, String voterpassword)
  {
	  this.voterID=voterID;
	  this.voterName=voterName;
	  this.voterpassword=voterpassword;  
  }

public Voting() {
	// TODO Auto-generated constructor stub
}

public int getVoterID() {
	return voterID;
}

public void setVoterID(int voterID) {
	this.voterID = voterID;
}

public String getVoterName() {
	return voterName;
}

public void setVoterName(String voterName) {
	this.voterName = voterName;
}

public String getVoteremailID() {
	return voteremailID;
}

public void setVoteremailID(String voteremailID) {
	this.voteremailID = voteremailID;
}

public String getVoterpassword() {
	return voterpassword;
}

public void setVoterpassword(String voterpassword) {
	this.voterpassword = voterpassword;
}

@Override
public String toString() {
	return "Voting [voterID=" + voterID + ", voterName=" + voterName + ", voteremailID=" + voteremailID
			+ ", voterpassword=" + voterpassword + "]";
}


}
