import * as path from 'path';
import * as winston from 'winston';
import {format} from 'winston';

export const logger = winston.createLogger({
    format: format.combine(
        format.timestamp({
            format: 'YYYY-MM-DD HH:mm:ss',
        }),
        format.simple(),
    ),
    level: 'info',
    transports: [
        new winston.transports.File({
            filename: path.join(__dirname, '..', '..', 'logfile.log'),
        }),
        new winston.transports.Console(),
    ],
});
