const exec = require('cordova/exec');
const PLUGIN_NAME = 'IFrameScript';

const IFrameScript = {
  InjectionTime: {
    START: 0,
    END: 1,
  },

  addScript: function (data) {
    return new Promise(function (resolve, reject) {
      if (typeof data.code == 'string') {
        exec(resolve, reject, 'IFrameScript', 'addScriptCode', [
          data.id,
          data.code,
          data.injectionTime || 0,
        ]);
      } else if (typeof data.file == 'string') {
        const path = data.file.replace(/^file:\/\//, ''); // Remove "file://" from the path, the plugin doesn't need it.

        exec(resolve, reject, 'IFrameScript', 'addScriptFile', [
          data.id,
          path,
          data.injectionTime || 0,
        ]);
      } else {
        reject(
          'addScript requires exactly one of code or file to be specified'
        );
      }
    });
  },

  // echo: function (phrase, cb) {
  //   exec(cb, null, PLUGIN_NAME, 'echo', [phrase]);
  // },
};

module.exports = IFrameScript;
