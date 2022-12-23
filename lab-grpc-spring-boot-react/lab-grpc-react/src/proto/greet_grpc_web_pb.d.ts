import * as grpcWeb from 'grpc-web';

import * as greet_pb from './greet_pb';


export class GreetServiceClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: any; });

  greet(
    request: greet_pb.GreetRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.RpcError,
               response: greet_pb.GreetResponse) => void
  ): grpcWeb.ClientReadableStream<greet_pb.GreetResponse>;

}

export class GreetServicePromiseClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: any; });

  greet(
    request: greet_pb.GreetRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<greet_pb.GreetResponse>;

}

