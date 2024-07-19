package onlinevote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class VotingManager 
{
    public static void main(String[] args) throws SQLException 
    {
        VotingManager v = new VotingManager();
        Scanner sc = new Scanner(System.in);

        while (true) 
        {
            System.out.println("1. Register voter");
            System.out.println("2. Login voter");
            System.out.println("3. Add candidate");
            System.out.println("4. Cast vote");
            System.out.println("5. View result");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            
            switch (choice) 
            {
                case 1:
                    System.out.print("Enter voter name: ");
                    String votername = sc.nextLine();
                    System.out.print("Enter voter ID: ");
                    int voterID = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter voter password: ");
                    String voterPassword = sc.nextLine();
                    Voting vote = new Voting(votername, voterID, voterPassword);
                    v.registerVoter(vote);
                    System.out.println("Registered successfully.");
                    break;

                case 2:
                    System.out.print("Enter voter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();
                    Voting loginVoter = v.loginVoter(id, password);
                    if (loginVoter != null) {
                        System.out.println("Logged in successfully.");
                    } 
                    else 
                    {
                        System.out.println("Invalid ID or password.");
                    }
                    break;

                case 3:
                    System.out.print("Enter candidate name: ");
                    String candidateName = sc.nextLine();
                    Candidate candidate = new Candidate(candidateName);
                    v.addCandidate(candidate);
                    System.out.println("Candidate added successfully.");
                    break;

                case 4:
                    System.out.print("Enter voter ID: ");
                    voterID = sc.nextInt();
                    System.out.print("Enter candidate ID: ");
                    int candidateID = sc.nextInt();
                    v.castVote(voterID, candidateID);
                    System.out.println("Vote cast successfully.");
                    break;

                case 5:
                    v.getResults();
                    break;

                case 6:
                    System.out.println("Exit...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    
    
    
    public void registerVoter(Voting vote) throws SQLException
    {
        String query = "INSERT INTO voters(voterName, voterID, voterpassword) VALUES(?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, vote.getVoterName());
            statement.setInt(2, vote.getVoterID());
            statement.setString(3, vote.getVoterpassword());
            statement.executeUpdate();
        }
    }

    
    
    public Voting loginVoter(int id, String password) throws SQLException 
    {
        String query = "SELECT * FROM Voters WHERE voterID = ? AND voterPassword = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, id);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) 
            {
                Voting vote = new Voting();
                vote.setVoterID(resultSet.getInt("voterID"));
                vote.setVoterName(resultSet.getString("voterName"));
                vote.setVoterpassword(resultSet.getString("voterPassword"));
                return vote;
            }
        }
        return null;
    }
    
    

    public void addCandidate(Candidate candidate) throws SQLException 
    {
        String query = "INSERT INTO Candidate (candidateName) VALUES (?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) 
        {
            statement.setString(1, candidate.getCandidateName());
            statement.executeUpdate();
        }
    }
    
    

    public void castVote(int voterID, int candidateID) throws SQLException 
    {
        String query = "INSERT INTO Votes (voterID, candidateID) VALUES (?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, voterID);
            statement.setInt(2, candidateID);
            statement.executeUpdate();
        }
    }
    

    public void getResults() throws SQLException
    {
        String query = "SELECT candidateName, COUNT(*) as VoteCount FROM Votes JOIN Candidate ON Votes.candidateID = Candidate.candidateID GROUP BY candidateName";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet results = statement.executeQuery())
        {
            while (results.next())
            {
                System.out.println("Candidate: " + results.getString("candidateName") + ", Votes: " + results.getInt("VoteCount"));
            }
        }
    }
}
