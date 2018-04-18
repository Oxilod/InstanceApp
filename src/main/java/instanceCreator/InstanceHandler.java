package instanceCreator;

import UI.ConfigController;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class InstanceHandler {
    private final static Logger logger = Logger.getLogger(InstanceHandler.class);
    private static BasicAWSCredentials awsCreds = new BasicAWSCredentials(instanceCreator.Credentials.AWS_ACCESS_KEY_ID, Credentials.AWS_SECRET_ACCESS_KEY);
    private static AmazonEC2Client ec2Client = new AmazonEC2Client(awsCreds).withRegion(Regions.EU_CENTRAL_1);

    public static void instance(){
        ConfigController config = new ConfigController();
        RunInstancesRequest instancesRequest = new RunInstancesRequest();
        instancesRequest.withImageId(config.selectedImageID).withInstanceType(config.selectedInstanceType).withMinCount(1).withMaxCount(1).withKeyName(config.selectedKeyName).withSecurityGroups(config.selectedGroupName);
        ec2Client.runInstances(instancesRequest);
        System.out.println("Instance is running");
    }

    public static List getRunningInstances() {
        DescribeInstancesRequest instancesRequest = new DescribeInstancesRequest();
        List<String> valuesT1 = new ArrayList<String>();
        valuesT1.add("running");
        Filter filter = new Filter("instance-state-name", valuesT1);
        DescribeInstancesResult instancesResult = ec2Client.describeInstances(instancesRequest.withFilters(filter));
        logger.info(instancesResult.toString());
        List<Reservation> instanceList = instancesResult.getReservations();
        List<String> instanceID = new LinkedList<String>();
        for (Reservation reservation : instanceList) {
            List<Instance> instances = reservation.getInstances();
            for (Instance instance : instances) {
                instanceID.add(instance.getInstanceId());
            }
        }
        return instanceID;
    }
    public static void terminateInstances(){
        TerminateInstancesRequest terminateInstancesRequest = new TerminateInstancesRequest();
        ec2Client.terminateInstances(terminateInstancesRequest);
    }
    public static void stopInstance(String instanceName){
        System.out.println(instanceName);
        StopInstancesRequest stopInstancesRequest = new StopInstancesRequest().withInstanceIds(instanceName);
        ec2Client.stopInstances(stopInstancesRequest);
    }
}
