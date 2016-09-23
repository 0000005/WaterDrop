package com.yin.waterdrop.spy.reporter;

import java.util.ArrayList;
import java.util.List;

import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import com.yin.waterdrop.rpc.entity.itf.NetWorkEntity;

public class NetworkGeter 
{
	public static List getCurrNetworkStatus(Sigar sigar) throws SigarException, InterruptedException
	{
		List<NetWorkEntity> netList = new ArrayList<NetWorkEntity>();
		String[] netIfs =sigar.getNetInterfaceList();
		for (int i = 0; i < netIfs.length; i++) 
		{
			NetWorkEntity netWorkEntity = new NetWorkEntity();
			long startTime = System.currentTimeMillis();
			NetInterfaceStat netStat =sigar.getNetInterfaceStat(netIfs[i]);
			long rxBytesStart = netStat.getRxBytes();    //接受
	        long txBytesStart = netStat.getTxBytes();    //发送
	        
	        Thread.sleep(1000);
	        
			netStat =sigar.getNetInterfaceStat(netIfs[i]);
			long rxBytesEnd = netStat.getRxBytes();    
            long txBytesEnd = netStat.getTxBytes();
            
			long endTime = System.currentTimeMillis();
			
			long downSpeed = (rxBytesEnd - rxBytesStart)*8/(endTime-startTime)*1000;  
			long upSpeed = (txBytesEnd - txBytesStart)*8/(endTime-startTime)*1000; 
			
			netWorkEntity.setIndex(i);
			netWorkEntity.setUpSpeed(upSpeed);
			netWorkEntity.setDownSpeed(downSpeed);
			
			netList.add(netWorkEntity);
		}
		return netList;
	}
}
