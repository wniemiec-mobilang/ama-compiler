//-----------------------------------------------------------------------------
//    Imports
//-----------------------------------------------------------------------------
const { tokenize, constructTree } = require('hyntax');
const stringify = require('json-stringify-safe');


//-----------------------------------------------------------------------------
//    Main
//-----------------------------------------------------------------------------
main();

function main() {
    if (process.argv.length < 3) {
        console.log('Usage: node ' + process.argv[1] + ' <html_content>');
        process.exit(1);
    }

    const normalizedHtml = process.argv[2].replaceAll("&nbsp", " ", );

    console.log(htmlToAst(normalizedHtml));
}


//-----------------------------------------------------------------------------
//    Functions
//-----------------------------------------------------------------------------
function htmlToAst(code) {
    const { tokens } = tokenize(code);
    const { ast } = constructTree(tokens);

    return stringify(ast);
}
