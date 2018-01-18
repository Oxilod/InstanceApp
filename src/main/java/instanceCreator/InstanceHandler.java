package instanceCreator;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2AsyncClient;
import com.amazonaws.services.ec2.model.RunInstancesRequest;



public class InstanceHandler {
    private static BasicAWSCredentials awsCreds = new BasicAWSCredentials(instanceCreator.Credentials.AWS_ACCESS_KEY_ID, Credentials.AWS_SECRET_ACCESS_KEY);
    private static AmazonEC2AsyncClient ec2Client = new AmazonEC2AsyncClient(awsCreds).withRegion(Regions.EU_CENTRAL_1);
    public static void instance(){
        RunInstancesRequest instancesRequest = new RunInstancesRequest();
        instancesRequest.withImageId("ami-0fc85a60").withInstanceType("t2.micro").withMinCount(1).withMaxCount(1).withKeyName("InstanceProject").withSecurityGroups("default");
        ec2Client.runInstances(instancesRequest);
        System.out.println("Instance is running");
    }
}
