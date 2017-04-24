from pandas import read_csv
from pandas import datetime
import pandas as pd
from matplotlib import pyplot
from statsmodels.tsa.arima_model import ARIMA
from sklearn.metrics import mean_squared_error
import numpy as np
import sys



def parser(x):
    return datetime.strptime(x, dateFormat)

def forecast():
    X = series[keyY].values
    size = int(len(X)-actionTime)
    train, test = X[0:size], X[size:len(X)]
    history = [x for x in train]
    predictions = []
    for t in range(len(test)):
        model = ARIMA(history, order=(5,1,0))
        model_fit = model.fit(disp=0)
        output = model_fit.forecast()
        yhat = output[0]
        predictions.append(yhat[0])
        obs = test[t]
        history.append(obs)
        print('predicted=%f, expected=%f' % (yhat, obs))
    error = mean_squared_error(test, predictions)
    print('Test MSE: %.3f' % error)

    time = series.index.values[size:len(X)]
    pyplot.plot(time,predictions,color="red")
    pyplot.plot(time,test)

    df = pd.DataFrame({keyX:time, keyY+"_prediction":predictions,keyY+"_actual":test})
    df.to_csv(outputFile, index=False, encoding='utf-8')

    pyplot.show()

def predict():
    X = series[keyY].values
    model = ARIMA(X,order=(5,1,0))
    model_fit=model.fit(disp=0)
    size = len(X)
    predictions = model_fit.predict(size,size+actionTime,typ='levels')
    print predictions
    result = np.concatenate((X, predictions))
    pyplot.plot(result)
    pyplot.show()



##################################################
###                                            ###
###                                            ###
###     TODO                                   ###
###     These Need to be passed as arguments   ###
###     Choose file                            ###
###     Choose time format                     ###
###     Choose average every hour,day          ###
###     Chooose to predict from which day      ###
###                                            ###
###                                            ###
###                                            ###
###                                            ###
##################################################


src = "/Users/ramyeid/Desktop/Proj/FYP/src/main/resources/"
inputFile = src+"PowerConsumption-Unit2(Protein).csv"
outputFile = src+"output.csv"
keyX = "Timestamp (PST)"
keyY = "Power (Watts)"
action = 1
actionTime = 12

##These two are sent ready from java.
dateFormat = "%m/%d/%y %H:%M" #"%Y-%m" #
average =  -1           #-1     -> No average
                        #'H'    -> Every hour


# print 'Number of arguments:', len(sys.argv), 'arguments.'
# print 'Argument List:', str(sys.argv)
#

series = read_csv(inputFile,parse_dates=[keyX], index_col=keyX, date_parser=parser)




if average != -1:
    series = series.resample(average).mean()
    if np.isnan(series[keyY]).any():
        series[keyY] =  np.nan_to_num(series[keyY])
        print "WARNING: CONTAINS NaN which means values may be wrong."

if action == 1:
    forecast()
if action == 2:
    predict()


