package mro.arcade.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Service to discover the Arduino device that is used as display in the {@link mro.arcade.game.view.renderer.ArduinoUDPRenderer}.
 * <p>
 * The implementation is based on the MDNS protocol that uses multicasts to inform other devices in the network segment about the offered services and allows them to connect via IP
 * and port.
 *
 * @author Michael Rodenbuecher
 * @since 2022-09-08
 */
public class DeviceDiscovery {

    /**
     * Logger.
     */
    public static final Logger LOG = LoggerFactory.getLogger(DeviceDiscovery.class);

    /**
     * The mDNS service handler.
     */
    private JmDNS jmdns;

    /**
     * The local IP address of this client. The address is needed in case multiple network adapters are available (regular ethernet and WIFI for example). In that case it is
     * important to choose the one that is connected to the same network that the Arduino device uses. Usually that is the WIFI one.
     */
    private InetAddress localAddress;

    /**
     * IP address of the device, discovered via mDNS.
     */
    private InetAddress deviceAddress;

    /**
     * Port of the device, discovered via mDNS.
     */
    private int devicePort;

    /**
     * Creates a new device discovery service.
     *
     * @param localAddress The local IP address of this client. The address is needed in case multiple network adapters are available (regular ethernet and WIFI for example). In
     *                     that case it is important to choose the one that is connected to the same network that the Arduino device uses. Usually that is the WIFI one.
     */
    public DeviceDiscovery(InetAddress localAddress) {
        this.localAddress = localAddress;
    }

    /**
     * Starts the discovery of the Arduino device. To make the usage easier in the context of this project, the execution of other source code will be blocked until a device was
     * discovered. In case no device was discovered, an exception is thrown.
     */
    public void discover() {
        LOG.debug("Setup device discovery service on network adapter ::= [{}]", localAddress);

        try {

            jmdns = JmDNS.create(localAddress);
            jmdns.addServiceListener("_arcade._udp.local.", new ServiceListener() {

                @Override
                public void serviceAdded(ServiceEvent serviceEvent) {
                    //  LOG.info("Added MDNS service ::= [{}]", serviceEvent.getInfo());
                }

                @Override
                public void serviceRemoved(ServiceEvent serviceEvent) {
                    //  LOG.debug("Removed MDNS service ::= [{}]", serviceEvent.getInfo());
                }

                @Override
                public void serviceResolved(ServiceEvent serviceEvent) {
                    LOG.debug("[{}]", serviceEvent.getInfo().getName());
                    LOG.debug("[{}] - Arcade Module found via MDNS, name ::= [{}], type ::= [{}], addresses ::= [{}], port ::= [{}]",
                            serviceEvent.getInfo().getName(),
                            serviceEvent.getInfo().getName(),
                            serviceEvent.getInfo().getType(),
                            serviceEvent.getInfo().getInetAddresses(),
                            serviceEvent.getInfo().getPort());
                    deviceAddress = serviceEvent.getInfo().getInetAddresses()[0]; // fine for the prototype, there should always be exactly one or none
                    devicePort = serviceEvent.getInfo().getPort();
                    stop();

                }
            });

            int retry = 0;
            while (++retry < 10) {

                if (deviceAddress != null) {
                    LOG.debug("Set device address to ::= [{}:{}]", deviceAddress, devicePort);
                    break;
                }

                Thread.sleep(1000);
            }


        } catch (Exception e) {
            LOG.debug("Exception during start of discovery service:", e);
        }


    }

    /**
     * Stops the discovery process.
     */
    public void stop() {
        if (jmdns != null) {
            try {
                jmdns.close();
                jmdns = null;
                LOG.debug("Stopped mDNS discovery...");
            } catch (Exception e) {
                LOG.debug("Exception during stop of discovery service:", e);
            }
        }
    }

    /**
     * Returns the IP address of the device, discovered via mDNS.
     *
     * @return the IP address
     */
    public InetAddress getDeviceAddress() {
        return deviceAddress;
    }

    /**
     * Returns the port address of the device, discovered via mDNS.
     *
     * @return the port
     */
    public int getDevicePort() {
        return devicePort;
    }

    public static void main(String[] args) throws UnknownHostException {
        DeviceDiscovery discovery = new DeviceDiscovery(InetAddress.getByName("192.168.51.51"));
        discovery.discover();
    }

}
