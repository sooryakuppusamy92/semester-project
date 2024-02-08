import json
import boto3
import logging
from botocore.exceptions import ClientError

logger = logging.getLogger()
logger.setLevel("INFO")


dynamodb = boto3.client('dynamodb',region_name='eu-west-1')
sns = boto3.client("sns")


def lambda_handler(event,context):    
    try:
        logger.info("Inside the handler function")
        logger.info(event)
        sns_parameters = event.get('Records', [])  
        if sns_parameters:
                # Extract the message from the SNS event
                for record in sns_parameters:
                    # Get the 'Sns' key from the first record
                    sns_data = record.get('Sns', {})                
                    # Get the 'Message' key from the 'Sns' data
                    message = sns_data.get('Message', None)                
                    if message is not None:
                        # Process the message
                        print("Message:", message)
                        # Split the message using ':'
                        filename,bucket_name,image_emotion = message.split(':')

                        # Print the results
                        print("filename:", filename)
                        print("bucket_name:", bucket_name)
                        print("image_emotion:", image_emotion)
                        
                        insert_table(filename,bucket_name,image_emotion)
                       
                    else:
                        print("'Message' key not found in 'Sns' data.")  
        
    except ClientError:
            logger.exception("errot")
            raise
    else:
        logger.info("successful")


 
def insert_table(filename,bucket_name,image_emotion):
    dynamodb.put_item(TableName='emotionDisplay', Item={'S3ID':{'S':bucket_name},'Filename':{'S':filename},'Emotion':{'S':image_emotion}})