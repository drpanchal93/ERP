/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

/**
 *
 * @author drashtipanchal
 */
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;


public class DeviceTypeEvent extends Event {
    public static final EventType<DeviceTypeEvent> DEVICE_TYPE_CHANGED = new EventType<>(ANY, "deviceTypeChanged");
    public              Node                       node;
    public              DeviceType                 deviceType;


    // ******************** Constructors **********************************
    public DeviceTypeEvent(final EventType<DeviceTypeEvent> EVENT_TYPE, final Node NODE, final DeviceType DEVICE_TYPE) {
        super(EVENT_TYPE);
        node       = NODE;
        deviceType = DEVICE_TYPE;
    }
}
