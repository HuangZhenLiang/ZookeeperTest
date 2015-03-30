import java.io.IOException;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;

//模拟配置文件变动
public class ZookeeperTest {
	private static ZooKeeper zk;
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException{
		
		zk=new ZooKeeper("127.0.0.1:2181",50000,new Watcher(){
			public void process(WatchedEvent event){
				if(event.getType()==Watcher.Event.EventType.NodeDataChanged)
			
				{
					try {
						System.out.println(new String(zk.getData("/root", true, null)));
					} catch (KeeperException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		
		//zk.create("/root", "root info".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		//System.out.println(new String(zk.getData("/root", true, null)));
		
		zk.exists("/root", true);
		Thread.currentThread().sleep(1000);
		zk.setData("/root", "root info3".getBytes(), -1);
		zk.setData("/root", "root info4".getBytes(), -1);
		zk.setData("/root", "root info5".getBytes(), -1);
		zk.setData("/root", "root info6".getBytes(), -1);
		zk.close();
	}
}
