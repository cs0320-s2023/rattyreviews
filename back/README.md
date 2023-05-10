# This is old, but some of the info on running is still relevant!


# Sprint 3: Server
### REPO LINK: https://github.com/cs0320-s2023/sprint-3-cflick-marora6.git 

### Authors:
* Connor Flick (cflick)
* Mahir Arora (marora6)

### Running
* _This is a Maven project on the backend._ Treat it as such!
* The server itself may be run using the command ``mvn exec:java -Dexec.mainClass="edu.brown.cs.student.main.server.Server"`` inside a bash shell.
* The tests for the server may be run using ``mvn test``.

### Design Notes
There are two API pathways, which are generally independendent of each other. 
##### CSV
The CSV pathway uses the server to open up the ``loadcsv``, ``searchcsv``, and ``viewcsv`` endpoints.
When these endpoints are called, they reach their respective handlers in the ``csvAPIHandlers`` folder to manage and parse the input.
This often includes the setting of parameters in the URL. Each of these handlers then call ``MapSerializer``, which converts the given ``Map<String, Object>``s (and only those ``Map<String, Object>``s, as specified) and converts them to legible JSON strings to output.

Notably, ``LoadCsvHandler`` holds a static version of the loaded data, which is considered on a high level to be the definitive source of information for loading. This means that _all people accessing the server have the same data loaded at the same time_, which is intentional.
Data may only be loaded from the ``exampleCsvData`` folder at this time, but this can be modified by changing the ``ALLOWABLE_DATA_PATH`` fields where they appear.

``SearchCsvHandler`` relies greatly on the ``csvParsingTools`` to do the actual work of searching a dataset. 

##### Weather
The weather pathway uses the server to open up the ``weather`` endpoint.
When that endpoint is called, it uses the ``WeatherHandler`` to manage the call and associated parameters in the URL.
In doing so, the handler utilizes ``WeatherSerializer`` along with ``Coordinates``, ``WeatherForecast``, and ``WeatherHelp`` to manage the data and convert it to a JSON string.
Further, the handler also uses ``WeatherCache`` to avoid needing to make calls for similar versions of the effectively the same coordinates.

Changing the caching parameters is relatively easy. All parameters exist as constants at the top of ``WeatherCache``, and their names describe their function (such as minimum distance, eviction time, etc.).


### Standing Bugs & Errors
* Searching csv files with non-rectangular shapes (i.e. one row has more entries than another) returns JSON responses suggesting IndexOutOfBoundsErrors with unusual and potentially unexpected attributes. The ``topBump.csv`` and ``middleBump.csv`` files demonstrate this well.
* Unexpected errors from CSV operations could generally be handled better, as showing a message and the full stack trace may not be the most user-friendly choice (or secure, for that matter).
* There are no tests for when the CSV handlers reach an otherwise unanticipated exception. If we could anticipate the exception, we would write a catch block for it and manage it!

### Tests
An overview of all the tests in the suite, and what they do. This is focused on the more involved _server and integration_ related tests; csv parsing and searching has been verified previously. 

#### CSV
##### LoadCsvHandlerTest
* errorWithoutParams: makes sure that an error indicating there is no accessible data when a filepath is not provided
* errorFileDoesntExist: checks that there is an error indicating there is no accessible data when the filepath doesn't exist
* loadsOneFile: checks that some file can be loaded, and that the internal state reflects this fact accurately
* loadsMultipleFiles: checks that several files may be loaded in succession, each changing internal state accurately
* loadsFromInsideFolder: checks that folders within the target folder are also accessible to be loaded

##### ViewCsvHandlerTest
* viewNoLoaded: checks that an error is given when viewcsv is called without loading first
* cannotViewNothing: checks that an error is given when no file has been loaded, even if loadcsv has been called.
* cannotViewGarbage: checks that an error is given when an inaccessible file tries to be loaded and then viewcsv is called
* oneViewOneLoad: checks that correct data is given after loading a certain file and viewing it
* oneViewMultiLoad: checks that correct data is given after loading several files in succession and viewing the data
* multiViewMultiLoad: checks for correct data being viewed after several steps of loading and viewing in a row.

##### SearchCsvHandlerTest
* searchNoDataLoad: checks for an error to be given when searching a csv without loading any file first
* searchBadDataLoad: checks that an error is given if an inaccessible file is loaded then searched
* searchNoParams: ensures that error_bad_params is given when searching without any parameters
* searchWithoutTerm: checks that an error_bad_params is given when searching without a searchTerm
* searchOneFileDefaults: checks for expected data to be returned when only searching with a search term and letting all other params fall to default values
* searchOneFileStrParameterized: checks that SearchCsvHandler is able to manage searching within a column using a string based header
* searchOneFileIntParameterized: checks that SearchCsvHandler is able to manage searching within a column using a int based index
* searchOneFileIntOOB: checks that an out-of-bounds error is given when searching for a column that falls outside the bounds of the loaded data
* searchOneFilePullMultipleRows: makes sure that SearchCsvHandler can provide multiple rows of results at once
* searchOneFileStrOOB: checks that an out-of-bounds error is given when searching for a column that doesn't have a header in the first row of the dataset
* searchOneFileBadTerm: checks that no data is provided when the searchTerm doesn't exist in the dataset
* searchOneFilePullNoRows: checks that no data is provided when the searchTerm does exist in the file, but certain parameters prevent the result's inclusion
* searchOneEmptyFile: ensures that SearchCsvHandler doesn't break when appropriately attempting to search an empty file.
* searchOneEmptyFileOOB: checks that an out-of-bounds error is given when searching outside the bounds of an empty file
* searchMultipleFiles: checks that state is maintained when loading and searching multiple files in succession

##### MapSerializerTest
* checkToJson: checks that toJson produces an expected JSON string given a map of various strings and objects
* onlyNullToJson: checks that an empty JSON string is provided when all the objects in the given map are null
* malformedDataThrowsFromJson: ensures that fromJson throws when the data provided cannot fit in a string, object map structure
* malformedDataThrowsToJson: checks that toJson throws when objects in the string, object map given cannot be serialized in a coherent way
* standardFromJson: checks that fromJson produces some map of strings and objects when the data provided is well-formed
* simpleSuccessExample: checks that simpleSuccessResponse produces some exepcted output JSON string
* simpleFailureExample: checks that simpleFailureResponse produces some expected JSON string
* simpleExceptionalExample: checks that exceptionalFailureResponse produces some known JSON string given a particular exception.


### Weather
* WeatherHandler: deals with all the potential inputs for lat and lon coordinates and determines weather to return a error or succcess message.
* WeatehrSerializer: deals with the serialization of the error and success methdods. It shows how these two potential results should be reutrned.
* WeatherCache: deals with caching certain coordinates that have been looked up. It stores locations in a map.
* WeatherHandlerTest: Tests all the potential outputs for the potential weather points.
