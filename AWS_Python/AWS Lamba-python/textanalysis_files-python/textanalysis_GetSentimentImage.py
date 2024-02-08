import json
import logging
import boto3
from botocore.exceptions import ClientError
from boto3.dynamodb.conditions import Key
import base64


logger = logging.getLogger()
logger.setLevel("INFO")


s3 = boto3.client("s3")
dynamodbclient = boto3.client('dynamodb')

def lambda_handler(event, context):
    try:
        logger.info("inside handler function!!!!") 
        response=getimage()
        image = response['Body'].read()
        
        return {
        'headers': {
            'Access-Control-Allow-Headers': 'Content-Type',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'OPTIONS,POST,GET',
            "Content-Type": "image/png" },
        'statusCode': 200,
        'body': base64.b64encode(image),
        'isBase64Encoded': True }
        
        
    except ClientError:
        logger.error("An error occurred", exc_info=True)
        raise

def getimage():
    try:
        table_name = "textanalysis_imagemapping"        
        dynamodb = boto3.resource('dynamodb', region_name="eu-west-1")
        ImageMappingtable = dynamodb.Table(table_name)
        
        response = ImageMappingtable.query(
        KeyConditionExpression=Key('record').eq('Final'))
        print(response['Items'])
        
        for item in response['Items']:
            
            bucket_name=item["bucket_name"]
            print(bucket_name)
             
            key=item["key"]
            print(key)              
                
            response = s3.get_object(Bucket=bucket_name,Key=key)
            if response['ResponseMetadata']['HTTPStatusCode'] == 200:
                print("image")
                print(response)
                return response
            break
                   
     
    except ClientError as e:
        print("Error retrieving items:", e)
    
    

    

            
