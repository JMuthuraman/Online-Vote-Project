package onlinevote;

public class Candidate 
{
  private int candidateID;
  private String candidateName;
  
  Candidate(String candidateName)
  {
	  this.candidateName=candidateName;
  }

public int getCandidateID() 
{
	return candidateID;
}

public void setCandidateID(int candidateID) 
{
	this.candidateID = candidateID;
}

public String getCandidateName() 
{
	return candidateName;
}

public void setCandidateName(String candidateName) 
{
	this.candidateName = candidateName;
}

@Override
public String toString() {
	return "Candidate [candidateID=" + candidateID + ", candidateName=" + candidateName + "]";
}
  
}
