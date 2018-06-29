import * as path from 'path';
import * as winston from 'winston';

export const logger = winston.createLogger({
    transports: [
        new winston.transports.File({
            filename: path.join(__dirname, '..', '..', 'logfile.log'),
            level: 'info',
        }),
    ],
});
