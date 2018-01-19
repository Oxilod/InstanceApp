package instanceCreator;

import UI.configController;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2AsyncClient;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;


public class InstanceHandler {
    private static BasicAWSCredentials awsCreds = new BasicAWSCredentials(instanceCreator.Credentials.AWS_ACCESS_KEY_ID, Credentials.AWS_SECRET_ACCESS_KEY);
    private static final Logger logger = Logger.getLogger("InstanceHandler.class");
    private static AmazonEC2AsyncClient ec2Client = new AmazonEC2Client(awsCreds).withRegion(Regions.EU_CENTRAL_1);

    public static void instance(){
        configController config = new configController();
        RunInstancesRequest instancesRequest = new RunInstancesRequest();
        instancesRequest.withImageId(config.selectedImageID).withInstanceType(config.selectedInstanceType).withMinCount(1).withMaxCount(1).withKeyName(config.selectedKeyName).withSecurityGroups(config.selectedGroupName);
        ec2Client.runInstances(instancesRequest);
        System.out.println("Instance is running");
        logger.info("Hello World");
    }

    public static List getRunningInstances() {
        DescribeInstancesRequest instancesRequest = new DescribeInstancesRequest();
        List<String> valuesT1 = new ArrayList<String>();
        valuesT1.add("running");
        Filter filter = new Filter("instance-state-name", valuesT1);
        DescribeInstancesResult instancesResult = ec2Client.describeInstances(instancesRequest.withFilters(filter));
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
}
