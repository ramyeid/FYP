from pandas import read_csv
from pandas import datetime
import pandas as pd
from matplotlib import pyplot
from statsmodels.tsa.arima_model import ARIMA
from sklearn.metrics import mean_squared_error
import numpy as np
import sys
import csv
import datetime as datet
import dateutil.relativedelta as dateutil
import os

#TODO divide into different scripts.
def parser(x):
    return datetime.strptime(x, dateFormat)

def forecastVsActual(keyY,series,actionTime,src):
    outputFile = src+"output.csv"
    X = series[keyY].values
    size = int(len(X)-actionTime)
    train, test = X[0:size], X[size:len(X)]
    history = [x for x in train]
    predictions = []
    for t in range(len(test)):
        model = ARIMA(history, order=(9,1,0))
        model_fit = model.fit(disp=0)
        output = model_fit.forecast()
        yhat = output[0]
        predictions.append(yhat[0])
        obs = test[t]
        history.append(obs)
        print('predicted=%f, expected=%f' % (yhat, obs))
    error = mean_squared_error(test, predictions)

    print('Test MSE: %.3f' % error)

    time = series[keyX].values[size:len(X)]


    df = pd.DataFrame({keyX:time,keyY+"_actual":test, keyY+"_prediction":predictions})
    df = df.set_index([keyX])
    df.to_csv(outputFile, index=True, encoding='utf-8')

    with open(outputFile,'a') as f:
        f.write("ERROR MSE: "+str(error));
        f.write("\n")
    f.close()


    # pyplot.plot(time,predictions,color="red")
    # pyplot.plot(time,test)
    # pyplot.show()



def predict(keyY,series,actionTime,src,dateFormat):
    outputFile = src+"output.csv"
    X = series[keyY].values
    model = ARIMA(X,order=(5,1,0))
    model_fit=model.fit(disp=0)
    size = len(X)
    predictions = model_fit.predict(size,size+actionTime-1,typ='levels')


    predicted = []
    currentDate = series[keyX][len(series[keyX])-1].date()
    for t in range(size,size+actionTime):
        currentDate = getNextDate(series,currentDate)
        value = "%s,%s" %(currentDate.strftime(dateFormat),predictions[t-size])
        predicted.append(value)



    copyDatatoTempFile(keyX,series,dateFormat,outputFile)

    with open(outputFile,'a') as f:
        for t in range(len(predicted)):
            f.write(predicted[t])
            f.write("\n")
        f.close()


#
    # date  = np.concatenate((series[keyX],date))
    # for t in range(0,len(result)):
    #     print str(date[t])+","+str(series[keyX][t])
    #
    # resultDate = np.concatenate((series[keyX].values,date))
    # df = pd.DataFrame({keyX:resultDate,keyY+"_result":result})
    # df = df.set_index([keyX])
    # df.to_csv(outputFile, index=True, encoding='utf-8')

    # pyplot.plot(result)
    # pyplot.show()



def forecastWithoutActual(keyY,series,actionTime,src):
    outputFile = src+"output.csv"
    X = series[keyY].values

    history = [x for x in X]
    currentDate = series[keyX][len(series[keyX])-1].date()
    forecasted = []
    for t in range(actionTime):
        model = ARIMA(history,order=(9,1,0))
        model_fit = model.fit(disp=0)
        output=model_fit.forecast()
        yhat = output[0]
        print("predicted=%f",yhat)
        history.append(yhat[0])

        currentDate = getNextDate(series,currentDate)
        value = "%s,%s"%(currentDate.strftime(dateFormat),yhat[0])
        forecasted.append(value)



    copyDatatoTempFile(keyX,series,dateFormat,outputFile)

    with open(outputFile,'a') as f:
        for t in range(len(forecasted)):
            f.write(forecasted[t])
            f.write("\n")
        f.close()

    #
    # pyplot.plot(history,color="red")
    # pyplot.show()




def getValueFromCSV(inputFile,keyX,keyY,setAverage):
    series = read_csv(inputFile,parse_dates=[keyX], date_parser=parser)

    if setAverage != "-1":
        series = series.resample(setAverage).mean()
        if np.isnan(series[keyY]).any():
            series[keyY] =  np.nan_to_num(series[keyY])
            print "WARNING: CONTAINS Null values which means values may be wrong."
    return series




def callAction(inputFile,action,actionTime,keyX,keyY,setAverage,src,resetCSV,data,dateFormat):
    series = getValueFromCSV(inputFile,keyX,keyY,setAverage)
    if action ==1:
        predict(keyY,series,actionTime,src,dateFormat)
    elif action ==2:
        forecastVsActual(keyY,series,actionTime,src)
    elif action ==3:
        forecastWithoutActual(keyY,series,actionTime,src)
    elif action ==4:
        continuousForecastWithoutActual(inputFile,keyY,keyX,series,actionTime,src,resetCSV,setAverage)
    elif action ==5:
        addValueToContinuousForecast(src,keyX,keyY,data,dateFormat)



def continuousForecastWithoutActual(inputFile,keyY,keyX,series,actionTime,src,resetCSV,setAverage):

    dataFile = src+"ContinuousForecast/Continuous_output.csv"
    outputForecastFile = src+"ContinuousForecast/Forecasts.txt"


    if resetCSV == 1 : ##new time series analysis
        ##clear all csv. and outputForecastFile.
        open(outputForecastFile, 'w').close()
        series = read_csv(inputFile)
        copyDatatoTempFile(keyX,series,dateFormat,dataFile)



    series = read_csv(dataFile,parse_dates=[keyX], date_parser=parser)

    if setAverage != "-1":
        series = series.resample(setAverage).mean()
        if np.isnan(series[keyY]).any():
            series[keyY] =  np.nan_to_num(series[keyY])
            print "WARNING: CONTAINS Null values which means values may be wrong."



    Y = series[keyY].values
    predicted = [x for x in Y]
    forecasted = []
    currentDate = series[keyX][len(series[keyX])-1].date()
    for t in range(actionTime):
        model = ARIMA(predicted,order=(9,1,0))
        model_fit = model.fit(disp=0)
        output=model_fit.forecast()
        yhat = output[0]
        currentDate = getNextDate(series,currentDate)
        predicted.append(yhat)

        value = "%s,%s" %(currentDate.strftime(dateFormat),yhat[0])
        forecasted.append(value)



    with open(outputForecastFile,'a') as f:
        for t in range(len(forecasted)):
            f.write(forecasted[t])
            f.write("\n")
        f.write("--------\n")
        f.close()



    #
    # series[keyX] = date
    # series[keyY] = history
    # series['Predicted & Actual'] = predicted
    #
    # # series.to_csv(outputFile,index=False)
    # #series['Actual & Predicted'] = history
    #
    # #series.to_csv(outputFile, index=False)
    #
    # pyplot.plot(predicted,color="red")
    # pyplot.show()
    #



def copyDatatoTempFile(keyX,series,dateFormat,file):
    print series[keyX][0]
    series[keyX] = pd.to_datetime(series[keyX]).dt.strftime(dateFormat)
    print series[keyX][0]

    series.to_csv(file,index=False)

def addValueToContinuousForecast(src,keyX,keyY,data,dateFormat):
    dataFile = src+"ContinuousForecast/Continuous_output.csv"

    series = read_csv(dataFile,parse_dates=[keyX],date_parser=parser)

    nextDate = getNextDate(series,series[keyX][len(series[keyX])-1].date())
    with open(dataFile,'a') as f:
        writer = csv.writer(f)
        writer.writerow([nextDate.strftime(dateFormat),data])
        f.close()


def getNextDate(series,currentDate):
    d1 = series[keyX][len(series[keyX])-3].date()
    d2 = series[keyX][len(series[keyX])-2].date()
    d3 = series[keyX][len(series[keyX])-1].date()


    seconds = ((abs((d2-d1).total_seconds()) + abs((d3-d2).total_seconds()) )/2)

    #in case we're jumping year to year
    if seconds>31449600:
        d3 = currentDate + dateutil.relativedelta(years=1)


    #in case we're jumping from month to month
    elif seconds> 2419200:
        d3 = currentDate + dateutil.relativedelta(months=1)



    #in case we're jumping from day to day
    elif seconds>82800:
        d3 = currentDate + dateutil.relativedelta(days=1)


    return d3


cwd = os.getcwd()
src = cwd+"/src/main/resources/"

##These two are sent ready from java.

action = int(sys.argv[4])
actionTime = int(sys.argv[5])
inputFile = sys.argv[1]
keyX = sys.argv[2]
keyY = sys.argv[3]
setAverage = (sys.argv[6])
#-1     -> No average
#'H'    -> Every hour


dateFormat = sys.argv[7]
# dateFormat = "%Y-%m" #
resetCSV = int(sys.argv[8])
data = float(sys.argv[9])
callAction(inputFile,action,actionTime,keyX,keyY,setAverage,src,resetCSV,data,dateFormat)





