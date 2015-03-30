import java.io.IOException;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
/**
 * 模拟集群管理
 */
public class ZookeeperNodeChange{
	private static ZooKeeper zk;
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException{
		
		zk=new ZooKeeper("127.0.0.1:2181",50000,new Watcher(){
			public void process(WatchedEvent event){
				//System.out.println(event.toString());

					try {
						System.out.println(zk.getChildren("/root", true));
					} catch (KeeperException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
		});
		
		//zk.create("/root", "root info".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		//System.out.println(new String(zk.getData("/root", true, null)));
		zk.exists("/root", true);
		zk.create("/root/server1","1".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
		zk.create("/root/server2","2".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
		zk.create("/root/server3","3".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
		zk.delete("/root/server3", -1);
		Thread.currentThread().sleep(1000);
		zk.close();
	}
}
