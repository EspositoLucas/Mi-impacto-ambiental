FROM node:latest AS angular

#aca es donde se despliegua la info de angular dentro del contenedor
RUN rm -rf /usr/share/nginx/html/* 
WORKDIR /file
COPY package.json ./file
RUN npm install -g npm@9.1.3 --silent
COPY . .
RUN npm run build 

FROM nginx:alpine

COPY --from=angular /file/dist/angular /usr/share/nginx/html
COPY ./nginx.config /etc/nginx/conf.d/default.conf
 