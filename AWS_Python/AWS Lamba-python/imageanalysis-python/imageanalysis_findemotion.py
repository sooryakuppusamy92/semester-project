import json
import boto3
import logging
from botocore.exceptions import ClientError

logger = logging.getLogger()
logger.setLevel("INFO")

s3 = boto3.resource('s3')
bucket_name = "sooryaimage"
my_bucket = s3.Bucket(bucket_name)
client=boto3.client('rekognition')
sns = boto3.client("sns")
dynamodb = boto3.resource('dynamodb')

def lambda_handler(event,context):
    file_name=""
    try:
        logger.info("Inside the handler function")
        deletetable(my_bucket)
        for my_bucket_object in my_bucket.objects.all():
            filename=my_bucket_object.key
            print(filename)
            image_emotion=call_detectFaceAPI(filename,bucket_name)
            print(image_emotion)
            if(image_emotion!=None):
                #insert_table(filename,bucket_name,image_emotion)
                response=filename + ":" + bucket_name + ":" +  image_emotion
                print("final:"+response)
                # Publish the message
                response = sns.publish(
                TopicArn='arn:aws:sns:eu-west-1:609980354649:imageanalysis-getemotion',
                Message=response,
                Subject='imageanalysis-getemotion'
                )
                print("inserted")
    except ClientError:
            logger.exception("errot")
            raise
    else:
        logger.info("successful")
 


    
def call_detectFaceAPI(filename,bucket_name):
    try:
        response = client.detect_faces(Image={'S3Object': {'Bucket':bucket_name,'Name':filename}},Attributes=['EMOTIONS'])
    except ClientError:
            logger.exception("Couldn't detect faces in %s.",filename)
            raise
    else: 
        for face in response['FaceDetails']:
            Emotions = face['Emotions']
            return find_emotion(Emotions)
    
    
    
def find_emotion(Emotions):
    Mface_emotion_confidence = float('-inf')  # Set to negative infinity initially
    face_emotion = None
    index=0
        
    for emotion in Emotions:
        print(emotion)     
        face_emotion_confidence = emotion['Confidence']
        if face_emotion_confidence > Mface_emotion_confidence:
            Mface_emotion_confidence = face_emotion_confidence
            Mface_emotion_confidence_index = index
            
        face_emotion = Emotions[index]
        return face_emotion["Type"]
        
def deletetable(my_bucket):
    table_name = 'emotionDisplay'
    table = dynamodb.Table(table_name)


    attribute_name = 'S3ID'
    attribute_value = 'sooryaimage'

    # Scan the table to find items with the specified attribute value
    response = table.scan(
    FilterExpression=f'#attr = :val',
    ExpressionAttributeNames={'#attr': attribute_name},
    ExpressionAttributeValues={':val': attribute_value}
    )
    print(response)  
    # Delete each matching item using DeleteItem
    for item in response['Items']:
        table.delete_item(
        Key={
            'Emotion': item['Emotion'],
            'Filename': item['Filename']
        }
            )
        

    print("DeleteItem operation completed.")
