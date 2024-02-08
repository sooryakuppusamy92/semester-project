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
                        bucket_name, key = message.split(':')

                        # Print the results
                        print("Bucket Name:", bucket_name)
                        print("Key:", key)
                        delete_table()
                        insert_table(bucket_name,key)
                    else:
                        print("'Message' key not found in 'Sns' data.")  
        
    except ClientError:
            logger.exception("errot")
            raise
    else:
        logger.info("successful")

def delete_table():
    table_name = 'textanalysis_imagemapping'
    primary_key = {
        'record': {'S': 'Final'}    
    }
    # Delete the item
    response = dynamodb.delete_item(
        TableName=table_name,
        Key=primary_key
    )

    print("Item deleted successfully:", response)
 
def insert_table(bucket_name,key):
    dynamodb.put_item(TableName='textanalysis_imagemapping', Item={'record':{'S':'Final'},'bucket_name':{'S':bucket_name},'key':{'S':key}})