import {
    quicktype,
    InputData,
    jsonInputForTargetLanguage,
} from "quicktype-core";
import fs from 'fs/promises';

const convertJsonToJsonSchema = async () => {
    const jsonInput = jsonInputForTargetLanguage('json-schema');
    const jsonString = await fs.readFile('./json.json', 'utf8')
    await jsonInput.addSource({
        name: 'root',
        samples: [jsonString]
    });

    const inputData = new InputData();
    inputData.addInput(jsonInput);

    const res = await quicktype({
        inputData,
        lang: 'json-schema'
    });

    fs.writeFile('./json-schema.json', res.lines.join('\n'), () => {})
}

convertJsonToJsonSchema()
