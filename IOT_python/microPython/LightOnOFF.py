from machine import Pin
import time
p0 = Pin(16, Pin.OUT)    # create output pin on GPIO0
while True:      
      p0.on()                # set pin to "on" (high) level
      time.sleep(2)           # sleep for 2 second
      p0.off()                # set pin to "off" (low) level
      time.sleep(2)           # sleep for 2 second
      p0.on() 
