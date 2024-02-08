
import paho.mqtt.client as mqtt
brokerURL = "broker.hivemq.com"
brokerPort = 1883 # port number for unencrypted connections



def on_connect(client, userdata, flags, rc):
   print("Connected with result code "+str(rc))

def on_message(client, userdata, message):
   print("Received message '" + str(message.payload) + "' on topic '"+ message.topic + "' with QoS " + str(message.qos))

def on_disconnect(client, userdata, rc):
    print("Client Got Disconnected")
    
mqttclient = mqtt.Client() #create cliet object

mqttclient.on_connect = on_connect
mqttclient.on_disconnect = on_disconnect
mqttclient.on_message = on_message

mqttclient.connect(brokerURL, brokerPort) #call connect function with URL and port number
#mqttclient.subscribe("M_IoT/s0585852/test", qos=0)
mqttclient.loop_start()
for i in range(1,10):
    mqttclient.publish(topic="M_IoT/s0585852/test", payload="DvvcD", qos=2, retain=True)

print("hello")
#mqttclient.subscribe("M_IoT/s0585852/test", qos=0)


#mqttclient.subscribe("M_IoT/+/test", qos=0)

